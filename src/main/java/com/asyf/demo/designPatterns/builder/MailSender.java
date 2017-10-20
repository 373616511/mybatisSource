package com.asyf.demo.designPatterns.builder;

/**
 * Created by Administrator on 2017/10/20.
 */
public class MailSender implements Sender {
    @Override
    public void sender() {
        System.out.println("this is mailsender!");
    }
}
