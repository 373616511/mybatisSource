package com.asyf.demo.test;

public class Test {

    public static void main(String[] args) {
        String[] ss = {"1", "2", "3"};
        String s = "2";
        int index = -ss.length - 1;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].equals(s)) {
                index = i;
                break;
            }
        }
        System.err.println(index);
    }
}
