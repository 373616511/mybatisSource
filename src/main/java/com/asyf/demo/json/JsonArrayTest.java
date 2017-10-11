package com.asyf.demo.json;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2017/10/11.
 */
public class JsonArrayTest {
    public static void main(String args[]) throws JSONException {
        String strs[] = {"one", "two", "three"};
        JSONArray myjson = new JSONArray(strs);
        for (int i = 0; i < myjson.length(); i++) {
            System.out.println(myjson.getString(i));
        }
    }
}
