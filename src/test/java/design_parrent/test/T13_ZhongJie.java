package design_parrent.test;

import design_parrent.pojo.p13_zhongjie.A;
import design_parrent.pojo.p13_zhongjie.B;
import design_parrent.pojo.p13_zhongjie.College;
import design_parrent.pojo.p13_zhongjie.MidImpl;
import org.junit.Test;

import java.util.ArrayList;

public class T13_ZhongJie {


    @Test
    public void t1() {

        MidImpl mid = new MidImpl();

        A tom = new A("tom", mid);
        B jerry = new B("jerry", mid);
        mid.setA(tom);
        mid.setB(jerry);

        tom.sendMsg("hi Jerry");
        jerry.sendMsg("ha tom");

        System.out.println("--------------- 订阅模式 -----------------");
        ArrayList<College> list = new ArrayList<>();

        list.add(new A("d1", mid));
        list.add(new A("d2", mid));
        list.add(new A("d3", mid));
        list.add(new A("d4", mid));
        list.add(new A("d5", mid));

        mid.setList(list);

        tom.pubMsg("--发布消息--");


    }

}
