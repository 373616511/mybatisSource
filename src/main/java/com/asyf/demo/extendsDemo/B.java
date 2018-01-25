package com.asyf.demo.extendsDemo;

/**
 * 父类静态代变量、 
 * 父类静态代码块、 
 * 子类静态变量、 
 * 子类静态代码块、 
 * 父类非静态变量（父类实例成员变量）、 
 * 父类代码块
 * 父类构造函数、 
 * 子类非静态变量（子类实例成员变量）、 
 * 子类代码块
 * 子类构造函数。 
 */
public class B extends A {
    private String a = "a：子类成员变量";

    {
        System.out.println("子类构造函数执行," + a);
    }

    public B() {
        System.out.println("子类构造函数！");
    }

    public static void main(String[] args) {
        new B();
    }
}
