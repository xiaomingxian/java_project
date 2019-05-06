package design_parrent.pojo.p1_single;

import java.io.Serializable;

/**
 * 饿汉式
 * 静态 创建对象
 */
public class Single1 implements Serializable {
    private Single1() {
    }

    private static Single1 single1 = new Single1();

    public static Single1 getInstance() {
        return single1;
    }

    /**
     * 防止反序列化------编写序列化对象读取的吃昂名方法--返回已经实例化的对象
     */
    private Object readResolve() {
        return single1;
    }




}
