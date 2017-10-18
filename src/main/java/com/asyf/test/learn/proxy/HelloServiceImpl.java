package com.asyf.test.learn.proxy;

/**
 * Created by Administrator on 2017/10/18.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        System.err.println("hello:" + name);
    }
}
