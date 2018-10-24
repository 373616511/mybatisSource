package com.asyf.demo.Html2Image;


import gui.ava.html.Html2Image;

import java.net.MalformedURLException;

public class Html2ImageTest {

    public static void main(String[] args) throws MalformedURLException {
        StringBuffer sb = new StringBuffer();
        sb.append(" <div >");
        //这个是主要内容 按照自己需要添加 sb.append( "内容");
        //
        sb.append("<p>\n" +
                "    <br/><span style=\"color:#ff0000\">sss</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <img src=\"http://dpic.tiankong.com/p9/um/QJ6256170643.jpg\" style=\"width: 215px; height: 164px;\"/>三生三世<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/>\n" +
                "</p>\n" +
                "<p>\n" +
                "    十里桃花。测试测试----------！！！\n" +
                "</p>");
        sb.append(" </div> ");
        
        Html2Image html2Image = Html2Image.fromHtml(sb.toString());
        // 这边如果设置false,图片不会自动根据内容设置长宽默认长1024 ,宽768
        html2Image.getImageRenderer().setAutoHeight(true);
        html2Image.getImageRenderer().setWidth(500);
        html2Image.getImageRenderer().saveImage("d:/demo.png");
        // 经过测试,div里添加的样式会失效,图片能转, 也可以直接传入链接
        // final Html2Image html2Image2 = Html2Image.fromURL(new URL("http://www.qq.com/"));
        // html2Image2.getImageRenderer().saveImage("d:/1.png");


    }
}
