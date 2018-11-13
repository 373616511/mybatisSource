package com.asyf.demo.decimal;

import java.math.BigDecimal;

public class DoMain {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("2.50");
        Integer integer = new Integer(20);
        //integer = null;
        System.out.println(bigDecimal.compareTo(BigDecimal.valueOf(integer)));
        int i = bigDecimal.intValue();
        System.out.println(i);
    }
}
