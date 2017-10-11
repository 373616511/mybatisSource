package com.asyf.demo.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/10/11.
 */
public class JsonTest {
    public static void main(String[] args) throws JSONException {
        JSONObject j = new JSONObject("{'name':'DarkRake','age':21}");
        //添加键值
        j.accumulate("sex","man");
        System.err.println(j);
        System.err.println(j.get("name"));
        System.err.println(j.get("age"));
        System.err.println(j.get("sex"));
    }
}
