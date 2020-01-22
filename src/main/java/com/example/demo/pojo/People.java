package com.example.demo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Document(indexName = "people3",type = "_doc")
public class People {

    @Id
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Text,analyzer = "ik_smart")
    private String description;

    @Field(type = FieldType.Text,analyzer = "hanlp")
    private String description2;

    @Field(type = FieldType.Text,analyzer = "pinyin")
    private String description3;

    @MultiField(mainField = @Field(type = FieldType.Text,analyzer = "ik_smart"),
            otherFields = @InnerField(suffix = "inner", type = FieldType.Text, analyzer = "pinyin"))
    private String description4;

    @MultiField(mainField = @Field(type = FieldType.Text,analyzer = "hanlp"),
            otherFields = @InnerField(suffix = "inner",type = FieldType.Text,analyzer = "pinyin"))
    private String description5;
}
