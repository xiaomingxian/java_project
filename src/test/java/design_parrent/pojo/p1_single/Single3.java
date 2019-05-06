package design_parrent.pojo.p1_single;

/**
 * 双重检测锁
 * 由于编译器优化和JVM底层模型原因，可能导致锁顺序发生变化偶尔会失效
 */
public class Single3 {


    private Single3() {
    }

    private static Single3 single3;

    public static Single3 getInstance() {
        //略。。。。
        return single3;
    }


}
