package com.asyf.demo.jdk.object;

/**
 * Created by Administrator on 2018/1/2.
 */
public class Test implements Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Test t = new Test();
        System.out.println(t != t.clone());
        System.out.println(t.getClass() == t.clone().getClass());
        System.out.println(t.equals(t.clone()));
        System.out.println(t.clone().equals(t));
    }
}
