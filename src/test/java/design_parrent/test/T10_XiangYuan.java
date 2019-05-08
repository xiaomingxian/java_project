package design_parrent.test;

import design_parrent.pojo.p10_xiangyuan.Flyweight;
import design_parrent.pojo.p10_xiangyuan.FlyweightFactory;
import design_parrent.pojo.p10_xiangyuan.UnShared;
import org.junit.Test;

public class T10_XiangYuan {

    @Test
    public void t1() {

        Flyweight x = FlyweightFactory.getFly("黑");

        Flyweight x2 = FlyweightFactory.getFly("黑");

        Flyweight y = FlyweightFactory.getFly("白");


        Flyweight y2 = FlyweightFactory.getFly("白");

        UnShared x1 = new UnShared("x", false);
        System.out.println(x1);

        System.out.println("-------------- 共享的部分可以复用  此处用的是 共享对象的属性 [非共享类]私有类没必要继承抽象类---------------");
        UnShared s1 = new UnShared("私有1", false);
        s1.setWb(x.getWb());
        s1.operate();

        UnShared s2 = new UnShared("私有2", false);
        s2.setWb(x.getWb());
        s2.operate();

        UnShared s3 = new UnShared("私有3", false);
        s3.setWb(y.getWb());
        s3.operate();

        UnShared s4 = new UnShared("私有4", false);
        s4.setWb(y.getWb());
        s4.operate();


    }


}
