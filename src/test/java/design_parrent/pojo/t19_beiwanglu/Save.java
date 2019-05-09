package design_parrent.pojo.t19_beiwanglu;

import lombok.Data;

/**
 * 备忘录
 */
@Data
public class Save {
    private String name;
    private String age;

    public Save() {
    }

    public Save(Origin origin) {
        this.name = origin.getName();
        this.age = origin.getAge();
    }




}
