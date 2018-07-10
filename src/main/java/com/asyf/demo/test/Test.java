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
        //System.err.println(index);
        String a = "123456";
        // System.out.println(a.substring(0, a.length()-1));
        int c = 7;
        int bb = c / 3;
        for (int i = c - 1; i / 3 >= bb && i > -1; i--) {
            System.out.println(i + "---" + i % 3 + "---" + i / 3);
        }
    }
}
