package com.asyf.demo.url;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/10/11.
 */
public class Test {

    //ak=hQ6ebaKI8gnRfhmszmdRT1AgWECDXLhX
    private static String URL = "http://api.map.baidu.com/staticimage/v2?ak=hQ6ebaKI8gnRfhmszmdRT1AgWECDXLhX&center=116.403874,39.914888&width=300&height=200&zoom=11";

    public static void main(String[] args) throws Exception {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //连接会话
        connection.connect();
        System.err.println("连接会话-------------------------------");
        //获取输入流
        InputStream in = connection.getInputStream();
        File file = new File("E:/baidu.png");
        FileOutputStream out = new FileOutputStream(file);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) >= 0) {
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        in.close();
        connection.disconnect();// 断开连接
        //打印结果
        //System.err.println(sb.toString());
    }
}
