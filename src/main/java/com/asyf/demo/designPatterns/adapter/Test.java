package com.asyf.demo.designPatterns.adapter;

/**
 * Created by Administrator on 2017/9/18.
 */
public class Test {
    public static void main(String[] args) {
        Target t = new Adapter();
        //适配器有两个方法
        t.sampleOperation1();
    }
}
