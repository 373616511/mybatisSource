package com.asyf.demo.designPatterns.singleton;

/**
 * Created by Administrator on 2017/10/20.
 */
public class Test {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance.equals(instance2));//true
    }
}
