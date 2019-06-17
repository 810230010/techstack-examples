package com.usher.elasticsearch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pms_product")
public class Product {
    private int id;
    private int brandId;
    private int productCategoryId;
    private String name;
    private String pic;
    private String description;
    private String subTitle;
}
