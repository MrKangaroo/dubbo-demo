package com.alibaba.dubbo.example.provider.service;

import com.alibaba.dubbo.example.CallBackListener;
import com.alibaba.dubbo.example.CallBackService;
import com.google.common.collect.Maps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author daisy
 * @desc
 * @create 2019/9/3
 */
public class CallBackServiceImpl implements CallBackService {

    private final Map<String, CallBackListener> listeners = Maps.newConcurrentMap();

    public CallBackServiceImpl() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        for (Map.Entry<String, CallBackListener> entry : listeners.entrySet()) {
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Exception e) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addListener(String key, CallBackListener callBackListener) {
        listeners.put(key, callBackListener);
        callBackListener.changed(getChanged(key));
    }

    private String getChanged(String key) {
        return key + " Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
