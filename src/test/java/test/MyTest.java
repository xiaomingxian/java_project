package test;

import java.util.*;


class Parent {

    private String name = "parent";

    void print() {

        System.out.println(this.name);
    }
}

class Son extends Parent {
    private String name = "son";

//    void print() {
//
//        System.out.println(this.name);
//    }

//    即使子类声明了与父类完全一样的成员变量，也不会覆盖掉父类的成员变量。
// 而是在子类实例化时，会同时定义两个成员变量，
// 子类也可以同时访问到这两个成员变量(this.i&super.i)，
// 但父类不能访问到子类的成员变量（父类不知道子类的存在）。
//
//    而具体在方法中使用成员变量时，
// 究竟使用的是父类还是子类的成员变量，
// 则由方法所在的类决定；即，方法在父类中定义和执行，
// 则访问的是父类的成员变量，
// 方法在子类中定义（包括覆盖父类方法）和执行，则访问的是子类的成员变量。


}


public class MyTest {

    public static void main(String[] args) throws Exception {

        //return 机制  return拷贝

//        System.out.println(getNum());
//        System.out.println(getNum2());
//
//        new Son().print();

//
//        excTest();


    }

    private static void excTest() {
        //        try {
        //            int x = 1 / 0;
        //        } catch (Exception e) {
        //
        //            throw  new Exception("xxx");
        //        }
    }


    static void change(List list) {
        list = new ArrayList();
        //arrayList.add("1");
        list.add("vv");

    }


    static String getNum() {
        //return 机制

        String A = null;
        try {
            A = "try";
            return A;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            A = "finally";
            //return A;
        }
        return null;


    }

    static String getNum2() {
        //return 机制

        String A = null;
        try {
            A = "try";
            return A;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            A = "finally";
            return A;
            //return "FIN";
        }


    }
}
