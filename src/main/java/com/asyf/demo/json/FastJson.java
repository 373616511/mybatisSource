package com.asyf.demo.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class FastJson {

    public static void main(String[] args) {
        A test = new A("test", new Date());
        String a  =  JSONObject.toJSONString(test);
        System.out.println(a);
    }

    public static class A {

        private String name;
        //加此注解
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date date;

        public A() {
        }

        public A(String name, Date date) {
            this.name = name;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
