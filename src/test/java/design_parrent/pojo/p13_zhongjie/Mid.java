package design_parrent.pojo.p13_zhongjie;

import lombok.Data;

import java.util.List;

@Data
public abstract class Mid {

    protected College A;
    protected College B;
    protected List<College> list;//广播形式


    public Mid() {
    }

    public abstract void contact(String msg, College college);

    public abstract void pubContact(String msg, College college);
}
