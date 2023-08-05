package com.ustc.elasticsearchdemo.service;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author :Yangyang Miao
 * @date :2023-08-05 17:35:00
 */
public class ESTestIndexDelete {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 没有相应的索引时，一定不要进行删除、修改、查询等操作，否则会报错，重复新增也会报错

        // 删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

        // 响应状态
        System.out.println(response.isAcknowledged());

        // 关闭客户端连接
        client.close();
    }
}
