package com.alibaba.dubbo.example.consumer.controller;

import com.alibaba.dubbo.example.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private DemoService demoService;


    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        String result = null;
        try {
            result = demoService.sayHello(name);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }

        return result;
    }
}