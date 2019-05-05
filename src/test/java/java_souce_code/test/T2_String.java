package java_souce_code.test;

public class T2_String {
    public static void main(String[] args) {
        String a = "ac";
        String b = "de";
        String c = "acde";
        String dd = "ac" + "de";
        String ss = new String("acde");
        System.out.println(dd == c);
        System.out.println(dd == ss);

        System.out.println(dd == ss.intern());//intern() 如果常量池中有这个值直接返回
    }

}
