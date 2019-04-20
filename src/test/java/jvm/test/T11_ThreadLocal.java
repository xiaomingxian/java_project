package jvm.test;

public class T11_ThreadLocal {

    ThreadLocal<Per> tl = new ThreadLocal();


    static class Per {
        String name;
    }


    public static void main(String[] args) {
        T11_ThreadLocal t = new T11_ThreadLocal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Per per = new Per();

                per.name="tom";
                t.tl.set(per);
                System.out.println("设置值："+per.name);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){}

                System.out.println("获取值："+t.tl.get());
            }
        }).start();
    }
}
