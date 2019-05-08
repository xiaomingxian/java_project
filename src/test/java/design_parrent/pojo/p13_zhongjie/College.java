package design_parrent.pojo.p13_zhongjie;

import lombok.Data;

//
@Data
public abstract class College {

    protected Mid mid;

    protected String name;

    //通过中介交流
    public College(Mid mid, String name) {
        this.mid = mid;
        this.name = name;
    }

    public abstract void sendMsg(String msg);
    public abstract void pubMsg(String msg);


}
