package jvm.pojo;

public class Parent {
    public static String AB="aaa";

    static {
        System.out.println("---父类加载");
    }
    public static void main(String[] args) {
    //    1.调用子类的常量 子类不会被加载
        System.out.println(Son.AB);

        //2.子类数组，子类不加载
        Son[] son=new Son[10];
        System.out.println("子类数组创建");
        //3.调用常量
        System.out.println(Son.A);

    }
}
