package gu_pao.p1_design_parrent.code.p2_single.pojo;

public class LazyDoubleCheck {

    /**
     * volatile 多线程下禁止指令重拍 指令重排可能会导致指令执行顺序不一致
     */
    private volatile static LazyDoubleCheck lazyDoubleCheck = null;

    private LazyDoubleCheck() {
    }

    public static LazyDoubleCheck getInstanceThreadSafe() {
        //第一层检查 一定概率上过滤掉 lazyDoubleCheck!=null 的情况 减少获取锁与释放锁的开销
        if (lazyDoubleCheck == null) {
            synchronized (LazyDoubleCheck.class) {
                //第二层 是在多个线程同时符合条件后进入第一层 后做严谨的判断
                if (lazyDoubleCheck == null) {
                    lazyDoubleCheck = new LazyDoubleCheck();
                }
            }
        }

        return lazyDoubleCheck;
    }

    public static LazyDoubleCheck getInstanceThreadUnSafeOneCheck() {
        //第一层检查 一定概率上过滤掉 lazyDoubleCheck!=null 的情况 减少获取锁与释放锁的开销
        if (lazyDoubleCheck == null) {
            synchronized (LazyDoubleCheck.class) {
                //第二层未进行 判断(多个线程同时突破第一层，第二层又没有条件判断-就会创建多个对象)
                lazyDoubleCheck = new LazyDoubleCheck();
            }
        }

        return lazyDoubleCheck;
    }


}
