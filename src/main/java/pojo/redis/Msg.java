package pojo.redis;

import java.io.Serializable;

public class Msg /*implements Serializable */ {
    String Id;
    String name;
    String msg;

    public Msg() {
    }

    public Msg(String id, String name, String msg) {
        this.Id = id;
        this.name = name;
        this.msg = msg;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
