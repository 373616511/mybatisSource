package com.asyf.demo.elasticsearch;

import com.google.gson.Gson;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Test {
    private static Gson gson = new Gson();

    public static void main(String args[]) throws Exception {
        String EsHosts = "127.0.0.1:9300,127.0.0.1:9301";
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "my-application")//设置集群名称
                .put("tclient.transport.sniff", true).build();//自动嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中
        //获取客户端
        TransportClient client = TransportClient.builder().settings(settings).build();
        String[] nodes = EsHosts.split(",");
        //添加节点
        for (String node : nodes) {
            if (node.length() > 0) {//跳过为空的node（当开头、结尾有逗号或多个连续逗号时会出现空node）
                String[] hostPort = node.split(":");
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostPort[0]), Integer.parseInt(hostPort[1])));
            }
        }
        // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        System.out.println();
        long a = System.currentTimeMillis();
        //index(client);
        //get(client);
        delete(client);
        //update(client);
        //search(client);
        long b = System.currentTimeMillis();
        System.out.println("耗时：" + (b - a));
        //释放资源
        client.close();
    }

    private static void search(TransportClient client) {
        //精确查询
        TermQueryBuilder t = QueryBuilders.termQuery("content", "爬");
        //rangge
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("line").gt("299").lt("310");
        //模糊查询
        String val = "后";
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("content", val);
        //match
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("content", val);
        //分词查询,分词后使用boolQuery
        IndicesAdminClient indicesAdminClient = client.admin().indices();

        AnalyzeRequestBuilder analyzeRequestBuilder = new AnalyzeRequestBuilder(client,
                AnalyzeAction.INSTANCE, "test_index", "后 我们Hello World 成龙原名陈港生");
        analyzeRequestBuilder.setAnalyzer("ik_smart");
        // ik_max_word  ik_smart
        //analyzeRequestBuilder.setTokenizer("ik_smart");//设置分析器才有效果，有待查证
        analyzeRequestBuilder.setTokenFilters("lowercase");//过滤器详情见文档
        AnalyzeResponse analyzeTokens = analyzeRequestBuilder.get();
        List<AnalyzeResponse.AnalyzeToken> list = analyzeTokens.getTokens();
        //Bool Query 用于组合多个叶子或复合查询子句的默认查询
        /*must 相当于 与 & =
                must not 相当于 非 ~   ！=
        should 相当于 或  |   or
        filter  过滤*/
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (AnalyzeResponse.AnalyzeToken term : list) {
            System.out.println(term.getTerm());
            boolQueryBuilder.should(QueryBuilders.fuzzyQuery("content", term.getTerm()));
        }
        BoolQueryBuilder b = QueryBuilders.boolQuery();
        //  b.must(QueryBuilders.rangeQuery("date").gte("2018-08-10").lt("2019-03-20"));
        // b.must(boolQueryBuilder);


        System.out.println("=========================================");
        SearchResponse searchResponse = client.prepareSearch("test_index")
                .setTypes("test_type")
                .addSort("date", SortOrder.ASC)//排序
                .setSearchType(SearchType.DEFAULT)//查询类型
                .setQuery(b)//替换不同的查询
                .setFrom(9149)
                .setSize(10)
                .addHighlightedField("content")//高亮
                .setHighlighterPreTags("<h1>")
                .setHighlighterPostTags("</h1>")
                .get();
        //获取命中数
        SearchHits hits = searchResponse.getHits();
        System.out.println("hits count " + hits.getTotalHits() + "--");
        //遍历
        int i = 0;
        for (SearchHit searchHit : hits) {
            System.out.print(++i + "----" + searchHit.getScore() + "---");
            System.out.println(searchHit.getSourceAsString());
            System.out.println("Map方式打印高亮内容");
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            System.out.println(highlightFields);
            if (highlightFields != null && highlightFields.get("content") != null) {
                Text[] text = searchHit.getHighlightFields().get("content").getFragments();
                if (text != null) {
                    for (Text str : text) {
                        System.out.println(str.string());
                    }
                }
            }
        }

    }

    private static void update(TransportClient client) throws Exception {
        /*UpdateRequest updateRequest = new UpdateRequest("test_index", "test_type", "test_id");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("gender", "male")//没有则添加
                .field("user", "user_test")
                .endObject());*/
        //There is also support for upsert. If the document does not exist,
        // the content of the upsert element will be used to index the fresh doc:
        IndexRequest indexRequest = new IndexRequest("test_index", "test_type", "test_id44");
        indexRequest.source(jsonBuilder()
                .startObject()
                .field("gender", "male")//没有则添加
                .field("user", "user_test44")
                .endObject());
        UpdateRequest updateRequest = new UpdateRequest("test_index", "test_type", "test_id4");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("gender", "male")//没有则添加
                .field("user", "user_test")
                .endObject())
                .upsert(indexRequest);//没有数据添加新纪录
        client.update(updateRequest).get();
    }

    public static void delete(TransportClient client) {
        //DeleteResponse response = client.prepareDelete("test_index", "test_type", "test__id").get();
        DeleteIndexResponse response = client.admin().indices().prepareDelete("test_index").get();
        System.out.println(response.isAcknowledged());
    }

    public static void get(TransportClient client) {
        GetResponse response = client.prepareGet("test_index", "test_type", "test_id")
                .setOperationThreaded(false)    // 线程安全
                .get();
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        String sourceAsString = response.getSourceAsString();
        System.out.println("\r\nindex:" + index + "\r\n" + "type:" + type + "\r\n"
                + "id:" + id + "\r\n" + "version:" + version + "\r\n" + "sourceAsString:" + sourceAsString);
    }

    public static void index(TransportClient client) throws UnknownHostException {
        int line = 1;
        // while (line < 5000000) {

        File file = new File("C:\\Users\\Administrator\\Desktop\\生命时空.txt");
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
                IndexRequestBuilder builder = client.prepareIndex();
                builder.setIndex("test_index");
                builder.setType("test_type");
                builder.setId("test_id_" + line);
                builder.setSource(s);
                builder.get();
                line++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Map<String, Object> json = new HashMap<String, Object>();
        /*json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out Elasticsearch");*/
    }
    // }
}
