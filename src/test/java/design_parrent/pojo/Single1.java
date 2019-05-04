package design_parrent.pojo;

/**
 * 饿汉式
 * 静态 创建对象
 */
public class Single1 {
    private Single1() {
    }

    private static Single1 single1 = new Single1();

    public static Single1 getInstance() {
        return single1;
    }

}
