package com.asyf.demo.elasticsearch.es5_6;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsUtils {
    public static String CLUSTER_NAME = "elasticsearch";//Elasticsearch集群名称
    public static String HOST_IP = "127.0.0.1";//Elasticsearch集群节点
    public static int TCP_PORT = 9300;//Elasticsearch节点TCP通讯端口
    private volatile static TransportClient client;//客户端对象,用于连接到Elasticsearch集群

    /**
     * Elasticsearch Java API 的相关操作都是通过TransportClient对象与Elasticsearch集群进行交互的。
     * 为了避免每次请求都创建一个新的TransportClient对象,可以封装一个双重加锁单例模式返回TransportClient对象。
     * 即同时使用volatile和synchronized。volatile是Java提供的一种轻量级的同步机制,synchronized通常称为重量级同步锁。
     *
     * @author moonxy
     */
    public static TransportClient getSingleTransportClient() {
        Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME).build();
        try {
            if (client == null) {
                synchronized (TransportClient.class) {
                    client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST_IP), TCP_PORT));
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void createIndex(String index, String type) throws IOException {
        TransportClient transportClient = getSingleTransportClient();
        CreateIndexRequestBuilder createIndex = transportClient.admin().indices().prepareCreate(index);
        /*XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties") //设置之定义字段
                .startObject("name").field("type", "text").field("analyzed", "standard").endObject() //设置分析器
                .startObject("age").field("type", "long").endObject()
                .startObject("class_name").field("type", "keyword").endObject()
                .startObject("birth").field("type", "date").field("format", "yyyy-MM-dd").endObject()//设置Date的格式
                .endObject()
                .endObject();*/
        //createIndex.addMapping(type, mapping);
        CreateIndexResponse res = createIndex.execute().actionGet();

    }

    //测试入口
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getSingleTransportClient();
        GetResponse getResponse = client.prepareGet("test", "list001", "5").get();
        System.out.println(getResponse.getSourceAsString());
        //创建索引
        createIndex("test2", "test_type");
    }
}
