package com.asyf.demo.jdk.finalTest;

/**
 * Created by Administrator on 2017/11/7.
 */
public class Test {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        StringBuffer buffer = new StringBuffer("hello");
        myClass.changeValue(buffer);
        System.out.println(buffer.toString());
    }
}

class MyClass {

    void changeValue(final StringBuffer buffer) {
        buffer.append("world");
    }
}
