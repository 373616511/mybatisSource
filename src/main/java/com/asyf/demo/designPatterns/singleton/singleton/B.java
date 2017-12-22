package com.asyf.demo.designPatterns.singleton.singleton;

/**
 * Created by Administrator on 2017/10/20.
 */
public class B {
    private static B b = new B();

    private A a = A.getInstance();

    private B() {

    }

    public static B getInstance() {
        System.out.println("B被调用");
        return b;
    }

    public void test() {
        System.out.println(a);
    }
}
