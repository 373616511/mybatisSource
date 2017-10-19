package com.asyf.test.learn.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/10/19.
 */
public class HelloServiceCgLib implements MethodInterceptor {

    private Object target;

    //创建代理对象
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        //回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("***CGLIB动态代理***");
        Object result = null;
        //反射方法前调用
        System.err.println("准备说hello");
        //执行方法，相当于调用HelloServiceImpl类的sayHello方法
        result = methodProxy.invokeSuper(o, objects);
        //反射方法后调用
        System.err.println("说过hello了");
        return result;
    }
}
