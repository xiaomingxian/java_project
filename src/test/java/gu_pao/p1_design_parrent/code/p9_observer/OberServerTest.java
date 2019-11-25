package gu_pao.p1_design_parrent.code.p9_observer;

import gu_pao.p1_design_parrent.code.p9_observer.pojo.Publish;
import gu_pao.p1_design_parrent.code.p9_observer.pojo.Question;
import gu_pao.p1_design_parrent.code.p9_observer.pojo.Receive;

public class OberServerTest {
    public static void main(String[] args) {
        //1 jdk的发布订阅
        jdkMq();


        //3 guava 实现发布订阅


    }

    private static void jdkMq() {
        Question question = new Question();
        question.setQuestionMsg("java到哪个版本了?");
        question.setUserName("tom");


        //订阅者
        Receive jerry = new Receive("jerry");
        Receive alice = new Receive("alice");

        Publish publish = new Publish();
        //添加消费者
        publish.addObserver(jerry);
        publish.addObserver(alice);
        //发布消息
        publish.publishQuestion(question);


    }
}
