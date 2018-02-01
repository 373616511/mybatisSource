package com.asyf.demo.collection.hashMapDemo;

import java.util.HashMap;
import java.util.Map;

public class DoMain {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("test", "word");
        System.out.println(map.get("test"));
    }
}
