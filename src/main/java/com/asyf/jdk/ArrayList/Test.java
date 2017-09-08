package com.asyf.jdk.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        String[] a = {"a"};
        System.err.println(a.hashCode());
        a = Arrays.copyOf(a, 2);
        System.err.println(a.length + "--" + a[0] + "--" + a[1]);

    }
}
