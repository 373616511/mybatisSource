package com.asyf.demo.htmlUnit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.css.StyleElement;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

public class DoMain {
    public static void main(String[] args) throws IOException {
        String url = "http://www.baidu.com";
        url = "http://search.dangdang.com/?key=%C1%F5%B4%C8%D0%C0&act=input";
        String str;

        //创建一个webClient,模拟浏览器
        //WebClient webClient = new WebClient();
        //使用FireFox读取网页
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);
        //使用Chrome读取网页
        //WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //打开的话，就是执行javaScript/Css
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);

        //获取页面
        HtmlPage page = webClient.getPage(url);


        //获取页面的title
        str = page.getTitleText();
        //System.out.println("Title:------" + str);

        //获取页面的XML代码
        str = page.asXml();
        System.out.println("Xml:------" + str);

        //获取页面的文本
        str = page.asText();
        //System.out.println("Text:------" + str);

        DomElement bigimg = page.getElementById("component_0__0__6612");
        DomNodeList<HtmlElement> li = bigimg.getElementsByTagName("li");
        for (HtmlElement htmlElement : li) {
            DomNodeList<HtmlElement> a = htmlElement.getElementsByTagName("a");
            String id = htmlElement.getAttribute("id");
            if (a != null) {
                HtmlElement e = a.get(0);
                String title = e.getAttribute("title");
                DomNodeList<HtmlElement> img = e.getElementsByTagName("img");
                HtmlElement htmlElement1 = img.get(0);
                String src = htmlElement1.getAttribute("data-original");
                System.out.println("title" + title);
                System.out.println(src);
                System.out.println(id);
            }
        }
        //关闭webClient
        webClient.close();
    }
}
