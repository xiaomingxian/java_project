package gu_pao.p1_design_parrent.code.p2_single.pojo;

import java.io.Serializable;

public class StaticInnerClass implements Serializable {


    private StaticInnerClass() {

    }

    public static StaticInnerClass getInstance() {
        return InnerClass.INNERCLASS;
    }


    //内部类当作成员处理了 内部的属性只有在调用的时候才会生效
    // (一个反例来论证。当你的内部类不是静态的时候。如果他是类级别的  他会允许 静态成员出现 事实不是)
    //是否写静态方法也可以(不行 虽然形式上类似但是(简单逻辑无法保证单例[除非套用其他模式]) 无法保证单例)内部类既是成员又是类
    static  class InnerClass {
        //不加final也是线程安全 -- 此处是按照类的属性？只加载一次？
        //jvm底层执行逻辑保证[教材说]
        static StaticInnerClass INNERCLASS = new StaticInnerClass();
    }


    private static StaticInnerClass staticInnerClass=null;
    static StaticInnerClass staticMethod(){
        System.out.println("-------------");
        return staticInnerClass=new StaticInnerClass();
    }


}
