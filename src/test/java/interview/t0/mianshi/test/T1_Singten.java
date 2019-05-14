package interview.t0.mianshi.test;

import org.junit.Test;

public class T1_Singten {
    //单例模式存在的问题
    @Test
    public void t1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                S.getInstance();
            }).start();
        }
    }
}

class S {
    /**
     * 双重检索--在加锁的前后都进行空值判断
     * <p>
     * 指令重排的情况
     * 原本创建对象的步骤   分配空间  初始化   引用指向
     * 发生了指令重排可能步骤：  分配空间  引用指向   初始化  --->导致空值判断的时候，判断不为空，但实际上还未进行初始化，拿到的实例有问题
     */

    private static volatile S s;//禁止指令重排

    private S() {
        System.out.println("-------实例创建");
    }

    public static /*synchronized*/ S getInstance() {//方法上加锁过于重量级--->使用双重检索机制
        if (s == null) {
            synchronized (S.class) {
                if (s == null) {
                    s = new S();
                }
            }
        }
        return s;
    }
}
