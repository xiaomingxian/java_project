package gu_pao.p1_design_parrent.code.p9_observer.pojo;

import java.util.Observable;

/**
 * 发布者 [提问者]
 */
public class Publish extends Observable {


    public void publishQuestion(Question question) {
        System.out.println("提问者：" + question.getUserName() + ",提问内容：" + question.getQuestionMsg());
        setChanged();
        notifyObservers(question);

    }

}
