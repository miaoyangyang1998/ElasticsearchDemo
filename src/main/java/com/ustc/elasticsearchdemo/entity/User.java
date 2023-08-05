package com.ustc.elasticsearchdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yangyang Miao
 * @date :2023-08-05 17:27:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;

    private String sex;

    private Integer age;
}
