package design_parrent.test;

import design_parrent.pojo.p4_prototype.Ship;
import org.junit.Test;

import java.util.Date;

/**
 * 原型模式
 */
public class T4_Prototype {

    //js中的继承  prototype

    /**
     * 深克隆的实现：
     * 反序列化
     * 手动将应用的属性进行克隆
     *
     * @throws Exception
     */

    @Test
    public void t1() throws Exception {
        Ship ship = new Ship("原型", new Date());
        Ship clone = (Ship) ship.clone();
        Object o = ship.clone();
        clone.setName("重新赋值");
        System.out.println(ship.getName() + "  " + ship.getDate());
        System.out.println(clone.getName() + "  " + clone.getDate());
        System.out.println(ship);
        System.out.println(clone);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(ship);
        System.out.println(o);

    }


}
