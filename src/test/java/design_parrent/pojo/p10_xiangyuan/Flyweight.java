package design_parrent.pojo.p10_xiangyuan;

import lombok.Data;


/**
 * 　所有具体享元类的超类或接口，通过这个接口，Flyweight可以接受并作用于外部状态。
 */
@Data
public abstract class Flyweight {
    private String nb;//内部状态---公共
    protected String wb;//外部状态---私有

    public Flyweight(String var, Boolean isNei) {
        if (isNei) this.wb = var;
        else this.nb = var;
    }

    //业务操作
    public abstract void operate();


}
