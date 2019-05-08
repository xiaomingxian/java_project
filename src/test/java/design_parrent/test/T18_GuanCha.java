package design_parrent.test;

import design_parrent.pojo.t18_guancha.Guan;
import design_parrent.pojo.t18_guancha.publish;
import org.junit.Test;

public class T18_GuanCha {

    /**
     * 未通知所有消费者？？？？？？
     */
    @Test
    public void t1() {
        publish publish = new publish();

        Guan guan1 = new Guan();
        Guan guan2 = new Guan();
        Guan guan3 = new Guan();

        publish.addObserver(guan1);
        publish.addObserver(guan2);
        publish.addObserver(guan3);

        publish.setStatus(100);

        System.out.println(guan1.getMystatus());
        System.out.println(guan2.getMystatus());
        System.out.println(guan3.getMystatus());

    }
}



