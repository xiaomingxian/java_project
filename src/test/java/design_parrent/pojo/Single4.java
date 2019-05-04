package design_parrent.pojo;

/**
 * 静态内部类
 */
public class Single4 {
    private static class sin {
        private static final Single4 single4 = new Single4();
    }

    public static Single4 getInstance() {
        return sin.single4;
    }
}
