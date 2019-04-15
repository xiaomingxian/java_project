package jvm.test;

import org.junit.Test;

public class T3_NeiCun {

    /**
     * 运行时常量池
     *
     */
    @Test
    public  void yunC(){
        //常量池中的StringTable  元素不可重复类似于 HashSet
        String a="abc";
        String b="abc";
        String c = new String("abc");
        System.out.println(a==b);
        System.out.println(a==c);
        //运行时常量--->将c的值拿出来扔进常量池
        System.out.println(a==c.intern());
    }


}
