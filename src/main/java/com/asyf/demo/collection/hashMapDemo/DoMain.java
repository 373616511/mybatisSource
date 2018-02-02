package com.asyf.demo.collection.hashMapDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DoMain {
    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        map.clear();//循环table数组置null


        map.put("test", "word");
        map.put("test2", "word");
        System.out.println(map.get("test"));

        //entrySet
        System.out.println("entrySet------start");
        Set<Map.Entry> set = map.entrySet();
        Iterator<Map.Entry> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.println(entry.getKey() + "----" + entry.getValue());
        }
        System.out.println("entrySet-------end");
        System.out.println(true ^ false);

        Set set1 = map.keySet();
    }
}
