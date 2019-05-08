package design_parrent.test;

import design_parrent.pojo.p14_mingling.Commond;
import design_parrent.pojo.p14_mingling.CommondImp;
import design_parrent.pojo.p14_mingling.Invoke;
import design_parrent.pojo.p14_mingling.Reciver;
import org.junit.Test;

public class T14_Mingling {

    /**
     * 避免调用者 ---直接与真是执行者打交道
     */

    @Test
    public void t1() {
        //命令对象
        Commond commond = new CommondImp(new Reciver());

        //    调用对象
        Invoke invok = new Invoke(commond);

        invok.startTask();


    }
}
