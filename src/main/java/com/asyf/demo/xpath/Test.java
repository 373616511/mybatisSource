package com.asyf.demo.xpath;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.PropertyParser;
import org.apache.ibatis.parsing.XNode;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
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
            node = (Node) x.evaluate("book", node, XPathConstants.NODE);
            System.err.println(node.getNodeName() + "--------"
                    + node.getNodeValue());
            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    String value = PropertyParser.parse(attribute.getNodeValue(), null);
                    System.err.println(attribute.getNodeName() + "--" + value);
                }
            }
            System.err.println("\r\n================获取子节点================");
            NodeList childNodes = node.getChildNodes();
            if (childNodes != null) {
                for (int i = 0, n = childNodes.getLength(); i < n; i++) {
                    Node node1 = childNodes.item(i);
                    //只查看nodeType是元素的节点
                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        System.err.println(node1.getNodeName() + "---" + node1.getTextContent());
                    }
                }
            }
            System.err.println("\r\n================NodeList================");
            XPathExpression xp = x.compile("bookstore/book");
            Object result = xp.evaluate(d, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            System.err.println("nodes.getLength():" + nodes.getLength());
            for (int i = 0; i < nodes.getLength(); i++) {
                Node item = nodes.item(i);
                System.err.println(item.getNodeName() + "--" + nodes.item(i).getNodeValue());
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
