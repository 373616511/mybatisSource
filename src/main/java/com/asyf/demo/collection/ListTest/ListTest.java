package com.asyf.demo.collection.ListTest;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 102; i++) {
            list.add(i + "");
        }
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //System.err.println(list.get(i));
            String a = "";
            a += list.get(i);
            if ((i + 1) < list.size()) {
                a += "---" + list.get(++i);
            }
            list2.add(a);
        }
        for (String s : list2) {
            System.err.println(s);
        }

    }
}
