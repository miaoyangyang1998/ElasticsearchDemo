package com.ustc.elasticsearchdemo.service;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @author :Yangyang Miao
 * @date :2023-08-05 17:33:00
 */
public class ESTestIndexCreate {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 没有相应的索引时，一定不要进行删除、修改、查询等操作，否则会报错，重复新增也会报错

        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        // 响应状态
        boolean acknowledged = response.isAcknowledged();
        System.out.println("索引操作：" + acknowledged);

        // 关闭客户端连接
        client.close();
    }
}
