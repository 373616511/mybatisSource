package com.asyf.demo.xpath;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.PropertyParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2017/10/27.
 */
public class Test {
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            System.err.println("XPath测试" + Test.class.getResource("/"));
            //// 创建Document对象
            String resource = "books.xml";
            inputStream = Resources.getResourceAsStream(resource);
            InputSource inputSource = new InputSource(inputStream);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(false);
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document d = db.parse(inputSource);
            // 创建XPath对象
            XPathFactory factory = XPathFactory.newInstance();
            XPath x = factory.newXPath();
            // 获取根元素
            // 表达式可以更换为/*,/rss
            Node node = (Node) x.evaluate("/*", d, XPathConstants.NODE);
            System.err.println(node.getNodeName() + "--------"
                    + node.getNodeValue());
            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    String value = PropertyParser.parse(attribute.getNodeValue(),null);
                    System.err.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
