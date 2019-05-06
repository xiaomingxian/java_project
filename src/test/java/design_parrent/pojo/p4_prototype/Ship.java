package design_parrent.pojo.p4_prototype;


import java.util.Date;

public class Ship implements Cloneable {
    //标记接口

    private String name;

    private Date date;

    public Ship() {
    }

    public Ship(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
