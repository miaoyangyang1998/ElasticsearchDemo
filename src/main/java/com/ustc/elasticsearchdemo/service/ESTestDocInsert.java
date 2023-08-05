package com.ustc.elasticsearchdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ustc.elasticsearchdemo.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author :Yangyang Miao
 * @date :2023-08-05 17:37:00
 */
public class ESTestDocInsert {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");

        User user = new User();
        user.setName("张三");
        user.setSex("男");
        user.setAge(25);

        // 向ES插入数据，必须使用JSON
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        // 关闭客户端
        client.close();
    }
}
