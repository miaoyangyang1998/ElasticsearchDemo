package com.ustc.elasticsearchdemo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author :Yangyang Miao
 * @date :2023-08-05 16:12:00
 */
public class ESTest_Client {
    public static void main(String[] args) {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 关闭客户端连接
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
