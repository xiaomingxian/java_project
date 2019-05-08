package design_parrent.pojo.p10_xiangyuan;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    //定义一个容器池
    static Map<String, Flyweight> pool = new HashMap<String, Flyweight>();

    //获取元素
    public static Flyweight getFly(String wb) {

        Flyweight flyweight = null;

        if (pool.containsKey(wb)) {
            flyweight = pool.get(wb);
            System.out.print("------------->从容器中取出：" + flyweight);
        } else {
            flyweight = new Shared(wb, true);
            pool.put(wb, flyweight);
            System.out.print("------------->先创建再从容器中取出：" + flyweight);

        }
        return flyweight;
    }


}
