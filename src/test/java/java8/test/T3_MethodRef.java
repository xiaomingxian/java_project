package java8.test;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class T3_MethodRef {


    /**
     * 1.实例::方法名
     * 2.类::静态方法名
     * 3.类::实例方法名   --->适用情况：第一个参数是方法的调用者，第二个参数是方法的参数时
     */

    @Test
    public void shili() {
        //当函数式对象的方法与lambda函数题的  参数类型与返回值相同时
        PrintStream p = System.out;
        Consumer<String> c = System.out::println;
        c.accept("xxx");
    }

    @Test
    public void ststicTest() {
        Comparator<Integer> c = Integer::compareTo;
        int compare = c.compare(1, 2);
        System.out.println(compare);
    }

    @Test
    public void leiMethod() {
        //断言的子接口
        BiPredicate<String, String> b = String::equals;
        boolean test = b.test("1", "1");
        System.out.println(test);
    }


}
