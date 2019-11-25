package gu_pao.p1_design_parrent.code.p9_observer.pojo;

import java.util.Observable;
import java.util.Observer;

public class Receive implements Observer {


    private String name;

    public Receive(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Publish p = (Publish) o;
        Question q = (Question) arg;
        System.out.println("================================");
        System.out.println(this.name + "收到消息：[" + q.getUserName() + "]:" + q.getQuestionMsg());
    }
}
