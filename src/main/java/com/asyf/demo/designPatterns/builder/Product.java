package com.asyf.demo.designPatterns.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */
public class Product {
    private List<String> parts = new ArrayList<String>();

    public void add(String partName) {
        parts.add(partName);
    }

    public void show() {
        System.out.println("----产品创建----");
        for (String part : parts) {
            System.out.println(part);
        }
    }
}
