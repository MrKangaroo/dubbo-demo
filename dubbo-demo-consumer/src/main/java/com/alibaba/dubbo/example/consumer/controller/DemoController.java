package com.alibaba.dubbo.example.consumer.controller;

import com.alibaba.dubbo.example.DemoService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class DemoController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private DemoService demoService;


    @RequestMapping("/sayHello")
    public String sayHello(String name) throws ExecutionException {

        Future<String> f = RpcContext.getContext().asyncCall(() -> demoService.sayHello(name));
        try {
            return f.get();
        } catch (InterruptedException e) {
            logger.error("InterruptedException");
        }

        return null;
    }

    @RequestMapping("/asyncTask")
    public String asyncTask(String param) throws ExecutionException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Future<String> f1 = RpcContext.getContext().asyncCall(() -> demoService.task5Second(param));

        Future<String> f2 = RpcContext.getContext().asyncCall(() -> demoService.task8Second(param));

        try {
            final String r1 = f1.get();
            final String r2 = f2.get();
            logger.info("asyncTask cost : {} s", stopwatch.elapsed(TimeUnit.SECONDS));
            return r1 + " " + r2;
        } catch (InterruptedException e) {
            logger.error("InterruptedException");
        }

        return null;
    }

    @RequestMapping("/asyncTask2")
    public String asyncTask2(String param) throws ExecutionException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        demoService.task5Second(param);
        Future<String> f1 = RpcContext.getContext().getFuture();
        demoService.task8Second(param);
        Future<String> f2 = RpcContext.getContext().getFuture();

        try {
            final String r1 = f1.get();
            final String r2 = f2.get();
            logger.info("asyncTask2 cost : {} s", stopwatch.elapsed(TimeUnit.SECONDS));
            return r1 + " " + r2;
        } catch (InterruptedException e) {
            logger.error("InterruptedException", e);
        }

        return null;
    }


    @RequestMapping("/asyncTask3")
    public String asyncTask3(String param) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        demoService.task10Second(param);
        logger.info("asyncTask3 cost : {} s", stopwatch.elapsed(TimeUnit.SECONDS));
        return "asyncTask3";
    }

    @RequestMapping("/syncTask")
    public String syncTask(String param) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        demoService.task12Second(param);
        logger.info("syncTask cost : {} s", stopwatch.elapsed(TimeUnit.SECONDS));
        return "syncTask";
    }
}