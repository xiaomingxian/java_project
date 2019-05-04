package design_parrent.test;

import design_parrent.pojo.Single1;
import design_parrent.pojo.Single2;
import design_parrent.pojo.Single4;
import org.junit.Test;

public class T1_Singleton {
    /**
     * 饿汉
     */
    @Test
    public void ehan() {
        Single1 instance = Single1.getInstance();
        Single1 instance2 = Single1.getInstance();

        System.out.println(instance == instance2);
    }

    /**
     * 同步 懒汉
     */
    @Test
    public void lanh() {
        Single2 instance = Single2.getInstance();
        Single2 instance2 = Single2.getInstance();
        System.out.println(instance == instance2);
    }
    /**
     * 双重检测--存在问题
     */


    /**
     * 静态内部类
     */
    @Test
    public void staticNeiBUClass(){

        Single4 instance = Single4.getInstance();
        Single4 instance2 = Single4.getInstance();

        System.out.println(instance==instance2);

    }



}
