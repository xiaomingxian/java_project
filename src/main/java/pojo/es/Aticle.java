package pojo.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * indexName: 索引名称
 * type: 文档的类型
 *
 * @Id: 索引库的id
 * index: 是否进行分词	analyzed:分词	 not_analyzed:不分词  no:不根据此字段进行检索
 * store: 是否存储
 * analyzer: 指定使用的分词器
 * type: 存储数据的类型
 * searchAnalyzer: queryStringQuery就是查询条件进行分词的分词器.
 */
@Document(indexName = "blog3", type = "article")
@Data
public class Aticle {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true, type = FieldType.Integer)
    private Integer id;
    @Field(index = FieldIndex.analyzed, analyzer = "ik", store = true, searchAnalyzer = "ik", type = FieldType.String)
    private String title;
    @Field(index = FieldIndex.analyzed, analyzer = "ik", store = true, searchAnalyzer = "ik", type = FieldType.String)
    private String content;

}