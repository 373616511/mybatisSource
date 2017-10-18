package com.asyf.test.learn.reflect;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/10/18.
 */
public class ReflectService {

    /**
     * service method
     *
     * @param name
     */
    public void sayHello(String name) {
        System.err.println("hello:" + name);
    }

    public static void main(String[] args) {
        try {
            //通过反射来创建对象
            Object o = Class.forName(ReflectService.class.getName()).newInstance();
            //获取服务方法
            Method method = o.getClass().getMethod("sayHello", String.class);
            //反射调用方法
            method.invoke(o, "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
