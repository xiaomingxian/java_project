package mybatis.pojo;

import lombok.Data;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Data
public class TableMsg {
    private String requst_uri;//请求表信息
    private Class<?> tableClass;//
    private String tableName;//注解标明的表名-没标注就是类名小写
    private List<String> columnNames;//实体类的属性信息
    private Mapper<?> mapper;//生成的代理类

    public TableMsg() {
    }

    public TableMsg(String requst_uri, Class<?> tableClass, String tableName, List<String> columnNames, Mapper<?> mapper) {
        this.requst_uri = requst_uri;
        this.tableClass = tableClass;
        this.tableName = tableName;
        this.columnNames = columnNames;
        this.mapper = mapper;

    }


}
