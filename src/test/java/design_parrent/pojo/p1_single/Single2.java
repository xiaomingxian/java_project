package design_parrent.pojo.p1_single;

public class Single2 {
    private static Single2 single2;

    private Single2() {
        if (single2 != null) {
            throw new RuntimeException("非法创建");
        }
    }


    public static Single2 getInstance() {
        synchronized (Single2.class) {//锁也可以直接加在 方法上
            if (single2 == null) {
                //此处可能会进来多个线程
                single2 = new Single2();
            }
        }
        return single2;
    }


}
