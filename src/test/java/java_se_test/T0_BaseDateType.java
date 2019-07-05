package java_se_test;

public class T0_BaseDateType {
    public static void main(String[] args) {
        //Long l=1111;//编译错误-整数默认int
        Long l = 1111L;

        //Float f=11;//错误编译错误 得加 F
        //float f=0.01;//编译错误    浮点默认double
        double df = 0.0;
        float f = 0.01F;


        char c1 = 'a';
        char c2 = '上';
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1 + c2);


    }
}
