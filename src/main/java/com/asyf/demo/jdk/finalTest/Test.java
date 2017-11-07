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

    void changeValue(StringBuffer buffer) {
        buffer = new StringBuffer();
        buffer.append("world");
        System.out.println("changeValue方法中：" + buffer.toString());
    }
}
