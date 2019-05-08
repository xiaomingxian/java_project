package design_parrent.pojo.p13_zhongjie;

public class B extends College {


    public B(String name, Mid mid) {
        super(mid, name);
    }

    @Override
    public void sendMsg(String msg) {
        mid.contact(msg, this);
    }

    @Override
    public void pubMsg(String msg) {
        mid.pubContact(msg, this);
    }

}
