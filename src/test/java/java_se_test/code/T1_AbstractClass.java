package java_se_test.code;

public abstract class T1_AbstractClass {

    //可以有成员变量
    private StringBuffer s;
    //可以有构造方法
    public T1_AbstractClass() {
    }

    //抽象方法
    protected abstract void t1();

    //可以有具体方法
    private void t2() {
    }
    //可以有静态方法
    private static void t3(){}
}
interface iA{
    //public iA(){}
    //成员全是public
    public String s="A";

    //报错
    //protected String ss="sss";

    //public static void s();//不能有静态

    // public abstract
    public abstract  void t1();

        }