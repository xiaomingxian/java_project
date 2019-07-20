package java_se_test;

import org.junit.Test;

public class T6ConstTest {


    /**
     * 1.7与之前版本比较  第一次出现的常量  intern()与堆中地址的比较
     */
    @Test
    public void tt() {

        //jdk1.7之后-首次出现的常量 不会复制一份到常量池中-只是在常量池中记录首次出现的实例引用(堆地址)
        // [intern()返回的也是常量记录的实例地址-所以第一处出现的常量--以下结果为 true]
        String s = new StringBuilder().append("计算机").append("软件").toString();
        System.out.println(s.intern() == s);

        //而java 不是第一次出现 但是 s1.intern()记录的是第一次的地址  所以不想等
        String s1 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s1.intern() == s1);

        //但是1.6及其之前  第一次出现的实例会复制到永久代 intern()返回的是永久代中的地址  即使是第一次出现的常量 s.intern()!=s
    }

}
