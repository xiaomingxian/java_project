package design_parrent.test;

import design_parrent.pojo.t19_beiwanglu.Origin;
import design_parrent.pojo.t19_beiwanglu.Save;
import design_parrent.pojo.t19_beiwanglu.SaveFactory;
import org.junit.Test;

public class T19_BeiWangLu {
    /**
     * 撤退1步
     */
    @Test
    public void t1() {
        //责任人
        SaveFactory saveFactory = new SaveFactory();

        //源类
        Origin tom = new Origin("tom", "19");

        Save save = tom.save();
        saveFactory.setSave(save);

        tom = new Origin("jerry", "20");

        //恢复
        tom.reCover(saveFactory.getSave());

        System.out.println(tom);
    }

    /**
     * 撤退n步
     */
    @Test
    public void recoverN() {
        //责任人
        SaveFactory saveFactory = new SaveFactory();

        //源类
        Origin tom = new Origin("tom", "19");

        saveFactory.Saves(tom.save());

        tom = new Origin("jerry", "20");
        saveFactory.Saves(tom.save());
        tom = new Origin("cat", "17");
        saveFactory.Saves(tom.save());
        tom = new Origin("dog", "16");
        saveFactory.Saves(tom.save());
        tom = new Origin("dock", "15");
        saveFactory.Saves(tom.save());

        //恢复
        for (int i = 0; i < 10; i++) {
            Save back = saveFactory.back();
            tom.reCover(back);
            if (back != null) System.out.println(tom);
        }

    }

}
