package spring_source_code.pojo;

import lombok.Data;

@Data
public class Car {
    public Car() {
        System.out.println("--------->Car 构造方法创建类");
    }

    public void init() {
        System.out.println("--------->Car 类初始化");

    }

    public void destory() {
        System.out.println("--------->Car 类销毁");

    }

    private String name;
    private String color;
}
