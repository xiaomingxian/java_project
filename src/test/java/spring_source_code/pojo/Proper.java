package spring_source_code.pojo;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Proper {
    //1.基本数值[各种类型]
    //2.可以写SPEL #{}
    //3.${}运行在环境变量里的值，配置文件中的变量最终会在环境变量中
    @Value("tom")
    private String name;
    @Value("#{10 - 2}")
    private Integer age;
    @Value("${address}")
    private String address;
}
