package com.asyf.demo.jdk.arrayList;


import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/22.
 */
public class A {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        if (StringUtils.isNotBlank(id)) {
            System.out.println(id.hashCode());
            return id.hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof A)
            return ((A) obj).id == id;
        return false;
    }

    public static void main(String[] args) {
        A a = new A();
        A a2 = new A();
        a2.setId("123");
        a.setId("123");
        Map<A, Object> map = new HashMap<>();
        map.put(a, "a");
        Object o = map.get(a2);
        System.out.println("o=" + o.toString());
    }
}
