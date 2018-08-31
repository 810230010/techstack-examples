package com.usher.elasticsearch.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "userdemo", type = "user")
@Data
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private int id;
    private String name;
    private int age;
    public User(){}
}
