package com.asyf.demo.jdk.ArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("b");

        String del = "a";
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).toString();
            list.remove(i--);
            //System.out.println(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            System.err.println(list.get(i));
        }

        /*list.removeAll(list);
        System.err.println(list.size());*/



        /*String[] a = {"a"};
        System.err.println(a.hashCode());
        a = Arrays.copyOf(a, 2);
        System.err.println(a.length + "--" + a[0] + "--" + a[1]);*/

    }
}
