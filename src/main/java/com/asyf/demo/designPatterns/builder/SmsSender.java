package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/10/20.
 */
public class SmsSender implements Sender {
    @Override
    public void sender() {
        System.out.println("this is sms sender!");
    }
}
