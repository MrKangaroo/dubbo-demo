/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.example.provider;

import com.alibaba.dubbo.example.DemoService;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service("demoServiceImpl")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }

    @Override
    public String task5Second(String param) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "task5Second " + param + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }

    @Override
    public String task8Second(String param) {
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "task8Second " + param + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }

    @Override
    public void task10Second(String param) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] task10Second " + param + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    }
}
