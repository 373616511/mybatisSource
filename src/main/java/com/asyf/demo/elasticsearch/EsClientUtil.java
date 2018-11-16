/*
package com.asyf.demo.elasticsearch;

import com.google.gson.Gson;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EsClientUtil {

    private static TransportClient client = null;
    private static BulkProcessor bulkProcessor = null;
    private static Gson gson = new Gson();

    private static final String EsHosts = "127.0.0.1:9300,127.0.0.1:9301";
    private static final String clusterName = "my-application";

    private EsClientUtil() {
        throw new RuntimeException("EsClientUtil is singleton");
    }

    */
/**
     * 获取客户端方法
     *
     * @return
     *//*

    public static TransportClient getEsClient() {
        if (client == null) {
            synchronized (EsClientUtil.class) {
                if (client == null) {
                    try {
                        initEsClient();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return client;
    }

    public static BulkProcessor getBulkProcessor() {
        if (bulkProcessor == null) {
            synchronized (EsClientUtil.class) {
                if (bulkProcessor == null) {
                    try {
                        initBulkProcessor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bulkProcessor;
    }

    private static void initBulkProcessor() {
        bulkProcessor = BulkProcessor.builder(getEsClient(), new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {
                //This method is called just before bulk is executed.
                // You can for example see the numberOfActions with request.numberOfActions()
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                //This method is called after bulk execution.
                // You can for example check if there was some failing requests with response.hasFailures()
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                //This method is called when the bulk failed and raised a Throwable
            }
        }).setBulkActions(10000)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
    }

    */
/**
     * 初始化es客户端
     *//*

    private static void initEsClient() throws Exception {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", clusterName)//设置集群名称
                .put("tclient.transport.sniff", true).build();//自动嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中
        //获取客户端
        client = TransportClient.builder().settings(settings).build();
        String[] nodes = EsHosts.split(",");
        //添加节点
        for (String node : nodes) {
            if (node.length() > 0) {//跳过为空的node（当开头、结尾有逗号或多个连续逗号时会出现空node）
                String[] hostPort = node.split(":");
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostPort[0]), Integer.parseInt(hostPort[1])));
            }
        }
    }

    public static void index(String index, String type, String id, String json, boolean isBulk) {
        if (isBulk) {
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.index(index);
            indexRequest.type(type);
            indexRequest.id(id);
            indexRequest.source(json);
            BulkProcessor bulkProcessor = getBulkProcessor().add(indexRequest);
        } else {
            getEsClient().prepareIndex(index, type, id).setSource(json).get();
        }
    }


    public static void main(String[] args) throws Exception {
        long a = System.currentTimeMillis();
        indexTest();
        long b = System.currentTimeMillis();
        System.out.println("耗时：" + (b - a) + "ms");
        //如果程序马上执行完就看不到数据插入，因为执行完还不到5s，bulkProcessor已经被销毁
        Thread.sleep(60000);
    }

    private static void indexTest() {
        int line = 1;
        int num = 0;
        while (num++ < 10) {
            System.out.println(num);
            File file = new File("C:\\Users\\Administrator\\Desktop\\《完美世界》（校对版全本）作者：辰东.txt");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String tempString = null;

                Map<String, Object> json = new HashMap<String, Object>();
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = bufferedReader.readLine()) != null) {
                    json.put("line", line);
                    json.put("content", tempString);
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                    json.put("date", sf.format(c.getTime()));
                    c.add(Calendar.DAY_OF_YEAR, 1);
                    //index json
                    String s = gson.toJson(json);
                    json.clear();
                    index("test_index", "test_type", "test_id_" + line, s, true);
                    line++;
                }
                //getBulkProcessor().flush();
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
*/
