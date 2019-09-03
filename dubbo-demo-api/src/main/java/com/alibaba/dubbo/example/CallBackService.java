package com.alibaba.dubbo.example;

/**
 * @author daisy
 * @desc
 * @create 2019/9/3
 */
public interface CallBackService {
    void addListener(String key, CallBackListener callBackListener);
}
