package gu_pao.p1_design_parrent.code.p2_single.pojo;

public class ThreadLocalSingleton {
    private ThreadLocalSingleton() {
    }


    private static ThreadLocal<ThreadLocalSingleton> threadLocal = new ThreadLocal<ThreadLocalSingleton>() {
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    public static ThreadLocalSingleton getInstance() {
        return threadLocal.get();
    }

}
