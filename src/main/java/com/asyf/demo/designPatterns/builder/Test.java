package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/10/20.
 */
public class Test {
    //建造者模式（Builder）
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
