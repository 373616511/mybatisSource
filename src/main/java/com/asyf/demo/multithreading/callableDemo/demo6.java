package com.asyf.demo.multithreading.callableDemo;

public class demo6 {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.err.println("www");
            }
        };
        new Thread(runnable).start();
    }
}
