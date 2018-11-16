package com.asyf.demo.decimal;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
        System.out.println(a - b);

        String c = new String("a");
        String d = new String("a");
        String e = "a";
        String f = "a";
        Set set = new HashSet();
        set.add(c);
        set.add(d);
        set.add(e);
        set.add(f);
        System.out.println(c.equals(d));
        System.out.println(f == e);
        System.out.println(set.size() + "----" + set.toString());
    }
}
