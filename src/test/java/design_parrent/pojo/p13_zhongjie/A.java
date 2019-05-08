package design_parrent.pojo.p13_zhongjie;

public class A extends College {

    public A(String name, Mid mid) {
        super(mid, name);
    }

    @Override
    public void sendMsg(String msg) {
        mid.contact(msg, this);//中介的实现类在内部进行消息转换
    }

    @Override
    public void pubMsg(String msg) {
        mid.pubContact(msg, this);
    }
}
