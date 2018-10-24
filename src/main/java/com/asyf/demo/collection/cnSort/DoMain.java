package com.asyf.demo.collection.cnSort;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoMain {
    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        list.add(new A("孙悟空"));
        list.add(new A("孙悟饭"));
        list.add(new A("唐僧"));
        list.add(new A("猪八戒"));
        list.add(new A("沙僧"));
        list.add(new A("安倍"));
        for (A a : list) {
            System.out.println(a);
        }
        System.out.println("=====================");
        //排序
        Collections.sort(list, new Comparator<A>() {
            @Override
            public int compare(A o1, A o2) {
                //Collator类是用来执行区分语言环境的String比较的，这里是选择CHINA
                Collator collator = Collator.getInstance(java.util.Locale.CHINA);
                //将该 string 转换为一系列可以和其他 collationkey 按位进行比较的位。
                return collator.getCollationKey(o2.getName()).compareTo(collator.getCollationKey(o1.getName()));
            }
        });
        //排序后结果
        for (A a : list) {
            System.out.println(a);
        }
    }

    public static class A {
        private String name;

        public A() {
        }

        public A(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
