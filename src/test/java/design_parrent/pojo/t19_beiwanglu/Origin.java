package design_parrent.pojo.t19_beiwanglu;


import lombok.Data;

/**
 * 源发器类
 */
@Data
public class Origin {

    private String name;
    private String age;

    //存储---将当前对象保存到备忘录类
    public Save save() {
        return new Save(this);
    }

    //恢复
    public void reCover(Save save) {
        if (save == null) return;
        this.name = save.getName();
        this.age = save.getAge();
    }

    public Origin(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
