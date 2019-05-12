package jvm.test.t14_classLoader;

import org.junit.Test;

import java.util.UUID;

/**
 * [在编译期能确定的常量]常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中
 * 本质上调用类并没有直接引用到定义常量的类，因此不会出发初始化
 * <p>
 * 此例：str直接存在了  T2_FinalTest  的常量池中  之后 T2_FinalTest与Final 没有任何关系了
 * 甚至可以删除 Final.class
 * 查看反编译信息：此处是常量  3: ldc           #4                  // String hello class
 * public void t1();
 * Code:
 * 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 3: ldc           #4                  // String hello class
 * 5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 * 8: return
 * <p>
 * 注记符是有底层的类来控制
 * 注记信息：ldc--->表示将int/float/String类型的常量从常量池中推送至栈顶[即将使用]
 */


/**
 * 当接口初始化时并不要求其父类初始化，只有真正使用到父接口时[如引入进口中所定义的常量时???会初始化？？public static final]，才会初始化
 */
public class T2_FinalTest {

    @Test
    public void t1() {
        System.out.println(Final.str);//ldc
        System.out.println(Final.i);//iconst_4表示将int类型 4推送至栈顶[此格式范围-1-5]
        System.out.println(Final.i2);//
        System.out.println(Final.s);//bipush   表示将单字节[-128~127] 的常量推送到栈顶
        System.out.println(Final.ss);//sipush 短整形[-32768~32767] 推送至栈顶
        //     public void t1();
        //    Code:
        //       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        //       3: ldc           #4                  // String hello class
        //       5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        //       8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        //      11: iconst_4
        //      12: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
        //      15: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        //      18: bipush        6
        //      20: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
        //      23: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        //      26: bipush        6
        //      28: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
        //      31: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        //      34: sipush        129
        //      37: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
        //      40: return
    }

    /**
     * 当一个类的常量不是在编译期可以确定，值就不会放入常量池
     * 这时在程序运行时，会导致主动使用这个常量所在的类，会导致这个类的初始化
     */
    @Test
    public void t2() {
        System.out.println(Final2.str);
    }


    /**
     * 数组形式 不尽兴初始化
     * 数组实例是在jvm运行时期生成
     * 数组实例形式   引用类型[L..一维  [[L..二维
     * 基本类型 int->[I Chart->[C   ....
     * [Ljvm.test.t14_classLoader.Final;
     */
    @Test
    public void t3() {
        Final[] f = new Final[2];
        System.out.println(f.getClass());//class [Ljvm.test.t14_classLoader.Final;[数组类型]---数组从属的类-有jvm在运行期间动态生成[类似于jvm]
        System.out.println(f.getClass().getSuperclass());

    }

}

class Final {
    public static final String str = "hello class";
    public static final int i = 4;
    public static final int i2 = 6;
    public static final short s = 6;
    public static final short ss = 129;


    static {
        System.out.println("类初始化");
    }
}

class Final2 {
    public static final String str = UUID.randomUUID().toString();


    static {
        System.out.println("类初始化");
    }
}
