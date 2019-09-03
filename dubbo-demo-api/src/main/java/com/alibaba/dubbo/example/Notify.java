package com.alibaba.dubbo.example;

/**
 * @author daisy
 * @desc
 * @create 2019/9/3
 */
public interface Notify {
    void onReturn(String result, int id, String name);
    void onThrow(Throwable ex, int id, String name);
}
