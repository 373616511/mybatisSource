package com.asyf.demo.decimal;

import java.math.BigDecimal;

public class DoMain {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("2.50");
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        //integer = null;
        System.out.println(bigDecimal.compareTo(BigDecimal.valueOf(a)));
        int i = bigDecimal.intValue();
        System.out.println(i);

        //Integer比较大小
        System.out.println(a.compareTo(b));
    }
}
