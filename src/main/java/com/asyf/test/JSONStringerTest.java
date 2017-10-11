package com.asyf.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by Administrator on 2017/10/11.
 */
public class JSONStringerTest {
    public static void main(String args[]) throws JSONException {
        demo();
        demo2();
    }

    private static void demo2() throws JSONException {
        JSONStringer js = new JSONStringer();
        // 创建book1
        JSONObject book1 = new JSONObject();
        book1.put("name", "Java");
        JSONArray ja1 = new JSONArray();
        String str1[] = {"LiuWANJUN", "XXX"};
        ja1.put(str1);
        book1.put("author", ja1);
        book1.put("price", "￥108");
        // 创建book2
        JSONObject book2 = new JSONObject();
        book2.put("name", "JavaScript");
        JSONArray ja2 = new JSONArray();
        String str2[] = {"LiSongFeng", "CaoLi"};
        ja2.put(str2);
        book2.put("author", ja2);
        book2.put("price", "￥108");

        js.object();
            js.key("Book");
            js.array();
                js.value(book1);
                js.value(book2);
            js.endArray();
        js.endObject();
        System.out.println(js.toString());
    }

    private static void demo() throws JSONException {
        JSONStringer js = new JSONStringer();
        js.array();//array()表明开始一个数组,即添加一个 [ ;

        js.object();//object()表明开始一个对象，即添加｛  ;
            js.key("name");//key()表示添加一个key;
            js.value("hj");//value()表示添加一个value;
            js.key("age");
            js.value(21);
        js.endObject();//endObject()表明结束一个对象，即添加 ｝ ;

        js.object();
            js.key("name");
            js.value("darkrake");
            js.key("age");
            js.value(21);
        js.endObject();

        js.endArray();//endArray()表明结束一个数组，即添加一个 ] ;
        System.out.println(js.toString());//[{"name":"hj","age":21},{"name":"darkrake","age":21}]
    }
}
