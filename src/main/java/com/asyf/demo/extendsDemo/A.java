package com.asyf.demo.extendsDemo;

public class A {

    static {
        System.out.println("A静态代码块");
    }

    {
        System.out.println("A代码块执行,");
    }
    private String id;

    public A() {
        System.out.println("父类构造函数...无参");
    }

    public A(String id) {
        this.id = id;
    }
}
