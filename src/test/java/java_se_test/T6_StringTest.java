package java_se_test;

import org.junit.Test;

public class T6_StringTest {


    /**
     * 字符串常量的拼接 与 字符串对比
     * <p>
     * 原因：JVM编译器对字符串做了优化，
     * <p>
     * 在编译时s1就已经被优化成“abcdef”，
     * <p>
     * s1和s2指向字符串常量池同一个字符串常量，所以==比较为true。
     */
    @Test
    public void constantCompare() {
        //编译器优化
        String s1 = "abc" + "def";

        String s2 = "abcdef";

        System.out.println(s1 == s2);
    }

    /**
     * 字符串+字符串变量 与 字符串常量对比
     * <p>
     * 字符串拼接时如果有[字符串变量]参与拼接，底层调用了StringBuffer可变字符串处理
     */
    @Test
    public void refCompare() {

        String s1 = "abc";
        /**
         * 此处变量的拼接
         * 变量的拼接底层调用的StringBuffer.append... 最后toString实例化了一个String对象
         */
        /**
         * 原理
         StringBuffer sb = new StringBuffer("");
         sb.append(s1).append("def");
         sb.toString();//
         //toString的底层实现 new 了一个对象
         // @Override
         //public synchronized String toString() {
         //    if (toStringCache == null) {
         //        toStringCache = Arrays.copyOfRange(value, 0, count);
         //    }
         //    return new String(toStringCache, true);
         //}
         */
        s1 = s1 + "def";


        String s2 = "abcdef";

        System.out.println(s1 == s2);

    }


}
