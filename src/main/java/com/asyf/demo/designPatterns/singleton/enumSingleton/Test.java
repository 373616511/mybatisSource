package com.asyf.demo.designPatterns.singleton.enumSingleton;

/**
 * Created by Administrator on 2017/12/22.
 */
public class Test {
    public static void main(String[] args) {
        EnumSingleton obj1 = EnumSingleton.getInstance();
        EnumSingleton obj2 = EnumSingleton.getInstance();
        //输出结果：obj1==obj2?true
        System.out.println("obj1==obj2?" + (obj1==obj2));
    }
}
