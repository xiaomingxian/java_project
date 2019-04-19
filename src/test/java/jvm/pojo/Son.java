package jvm.pojo;

public class Son extends Parent {
    public static final String A = "a";

    static {
        System.out.println("---子类加载");
    }
    public Son() {
    }
}
