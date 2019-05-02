package spring_source_code.pojo;


import lombok.Data;

@Data
public class T1P {
    public T1P() {
    }

    public T1P(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private Integer age;

}
