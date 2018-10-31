package com.asyf.demo.enumDemo;

public class DoMain {

    public static void main(String[] args) {
        System.out.println(Test.a.name());
        System.out.println(Test.values().toString());
        for (Test test : Test.values()) {
            System.out.println(test.getCommonClass());
        }
    }
}
