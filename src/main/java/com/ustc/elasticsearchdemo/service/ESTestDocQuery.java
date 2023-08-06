package com.ustc.elasticsearchdemo.service;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * 全量查询、条件查询、分页查询、查询排序、过滤字段、范围查询、模糊查询、高亮查询、聚合查询、分组查询
 * @author :Yangyang Miao
 * @date :2023-08-06 10:43:00
 */
public class ESTestDocQuery {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        SearchRequest request = new SearchRequest();
        request.indices("user");

        // 1. 全量查询 查询索引中全部的数据
        /*request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        request.source(builder);*/

        //===========================================================================================

        // 2. 条件查询 查年龄是30的
        /*request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)));
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        request.source(builder);*/

        //===========================================================================================

        // 3. 分页查询
        /*SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // (当前页码 - 1) * 每页显示数据条数 第2页 (2-1)*2
        builder.from(2); // 当前页起始的数据条数(位置) 第一页：第0条、第1条，第2页：第2条
        builder.size(2); // 每页2条
        request.source(builder);*/

        //===========================================================================================

        // 4. 查询排序
        /*SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.DESC); // 降序
        request.source(builder);*/

        //===========================================================================================

        // 5. 过滤字段 （包含和排除，下面的includes和excludes有一个为空才能起作用）
        /*SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String[] excludes = {};
        String[] includes = {"name"};
        builder.fetchSource(includes, excludes);
        request.source(builder);*/

        //===========================================================================================

        // 6. 组合查询
        /*SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 必须满足
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 30));
        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));

        builder.query(boolQueryBuilder);
        request.source(builder);*/

        //===========================================================================================

        // 7. 范围查询
        /*SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
        // 大于等于
        rangeQueryBuilder.gte(30);
        // 小于
        rangeQueryBuilder.lt(40);

        builder.query(rangeQueryBuilder);
        request.source(builder);*/

        //===========================================================================================

        // 8. 模糊查询
        /*SearchSourceBuilder builder = new SearchSourceBuilder();
        // 查询王五。差1个字符也能查到，例如王五1、王五2
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "王五").fuzziness(Fuzziness.ONE);
        builder.query(fuzzyQueryBuilder);
        request.source(builder);*/

        //===========================================================================================

        // 9. 高亮查询
        /*SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "张三");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");
        builder.highlighter(highlightBuilder);
        builder.query(termsQueryBuilder);
        request.source(builder);*/

        //===========================================================================================

        // 10. 聚合查询(求最大值最小值等操作)
        /*SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age"); // 自己取个名maxAge
        builder.aggregation(aggregationBuilder);
        request.source(builder);*/

        //===========================================================================================

        // 11. 分组查询
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        builder.aggregation(aggregationBuilder);
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }
}
