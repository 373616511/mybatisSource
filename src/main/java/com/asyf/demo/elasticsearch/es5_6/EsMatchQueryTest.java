package com.asyf.demo.elasticsearch.es5_6;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

public class EsMatchQueryTest {

    /**
     * 详细检索过程
     *
     * @author moonxy
     */
    public void esMatchQuery() {
        //构造查询对象的工厂类 QueryBuilders,matchQuery全文查询,Operator.AND指定分词项之间采用AND方式连接,默认是OR
        MatchQueryBuilder matchQuery = QueryBuilders
                .matchQuery("song", "今岁")
                .operator(Operator.AND);

        //构造HighlightBuilder对象,设置需要高亮的字段并自定义高亮标签
        HighlightBuilder highlighter = new HighlightBuilder()
                .field("song")
                .preTags("<span stype=\"color:red\">")
                .postTags("</span>");

        //获取传输客户端TransportClient对象,指定要搜索的索引名,设置查询字段和高亮,并设置一次查询返回文档的数量
        SearchResponse response = EsUtils
                .getSingleTransportClient()
                .prepareSearch("test")
                .setQuery(matchQuery)
                .highlighter(highlighter)
                .setSize(100)
                .get();

        //通过上面获得的SearchResponse对象,取得返回结果
        SearchHits hits = response.getHits();
        //搜索到的结果数
        System.out.println("共搜索到:" + hits.getTotalHits());

        //遍历SearchHits数组
        for (SearchHit hit : hits) {
            System.out.println("Source:" + hit.getSourceAsString());//返回String类型的文档内容
            System.out.println("Source As Map:" + hit.getSource());//返回Map格式的文档内容
            System.out.println("Index:" + hit.getIndex());//返回文档所在的索引
            System.out.println("Type:" + hit.getType());//返回文档所在的类型
            System.out.println("ID:" + hit.getId());//返回文档的id
            System.out.println("Source:" + hit.getSource().get("price"));//从返回的map中通过key取到value
            System.out.println("Score:" + hit.getScore());//返回文档的评分
            //getHighlightFields()会返回文档中所有高亮字段的内容，再通过get()方法获取某一个字段的高亮片段,最后调用getFragments()方法，返回Text类型的数组
            Text[] texts = hit.getHighlightFields().get("song").getFragments();
            if (texts != null) {
                //遍历高亮结果数组,取出高亮内容
                for (Text text : texts) {
                    System.out.println(text.string());
                }
            }
        }
    }

    //测试入口
    public static void main(String[] args) {
        new EsMatchQueryTest().esMatchQuery();
    }
}
