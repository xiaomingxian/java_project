package gu_pao.p1_design_parrent.code.p2_single.pojo;

public class Lazy {


    private static Lazy lazy = null;

    private Lazy() {
    }

    public static Lazy getInstance() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }

    /**
     * jdk1.6后对synchronized做了优化,性能提升了不少
     * 但是 synchronized 加在静态方法上可能会锁住整个类
     *
     * @return
     */
    public static synchronized Lazy getInstanceThreadSafe() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }


    private Object readResolve() {
        System.out.println("----------");
        return lazy;
    }
}
