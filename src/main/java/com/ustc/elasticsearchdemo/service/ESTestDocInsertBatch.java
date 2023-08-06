package com.ustc.elasticsearchdemo.service;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author :Yangyang Miao
 * @date :2023-08-06 09:23:00
 */
public class ESTestDocInsertBatch {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 批量插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "张三"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "李四"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "王五"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        client.close();
    }
}
