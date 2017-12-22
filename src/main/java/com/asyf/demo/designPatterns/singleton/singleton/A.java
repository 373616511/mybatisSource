package com.asyf.demo.designPatterns.singleton.singleton;

/**
 * Created by Administrator on 2017/10/20.
 */
public class A {
    private static A a = new A();

    private B b = B.getInstance();

    private A() {

    }

    public static A getInstance() {
        System.out.println("A被调用");
        return a;
    }

    public void test() {
        System.out.println(b);
    }
}
