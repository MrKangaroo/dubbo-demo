package com.alibaba.dubbo.example.consumer;

import com.alibaba.dubbo.example.Notify;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daisy
 * @desc
 * @create 2019/9/3
 */
public class NotifyImpl implements Notify {
    public Map<Integer, Object> ret = new HashMap<Integer, Object>();
    private String param;

    @Override
    public void onReturn(String result, int id, String name) {
        ret.put(id, result);
        System.out.println("onReturn: " + result);
    }

    @Override
    public void onThrow(Throwable ex, int id, String name) {
        ret.put(id, ex);
        System.out.println("onThrow: " + ex);
    }
}
