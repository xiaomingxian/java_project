package java8.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class T2_FourInterface {

    /**
     * 四大核心函数式接口
     */




    /**
     * consumer
     * 生产类型--有去无回
     */
    @Test
    public void consumer() {
        c(1000, (x) -> System.out.println("哈哈哈哈：" + x));
    }


    public void c(long i, Consumer<String> con) {
        con.accept("" + i);
    }

    /**
     * Supplier
     * 供给类型「生产类型」
     */
    @Test
    public void supp() {
        List produce = produce(10, () -> (int) (Math.random() * 10) + 1);//默认0-1
        System.out.println(produce);
    }

    private List produce(int size, Supplier<Integer> supplier) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            int x = supplier.get();
            list.add(x);
        }
        return list;
    }

    /**
     * 函数型接口：Function<R, T>
     */
    @Test
    public void fun() {
        String xxx = doSomeThing("xxx", (x) -> x.toUpperCase());
        System.out.println(xxx);

    }

    private String doSomeThing(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * 断言接口「过滤」Predicate：boolean类型返回值
     */
    @Test
    public void duan() {
        List<String> ss = Arrays.asList("xscs", "ss", "asa", "sdsas");
        List choice = choice(ss, (s) -> s.length() > 3);
        System.out.println(choice);
    }


    private List choice(List<String> list, Predicate<String> predicate) {

        ArrayList<Object> list1 = new ArrayList<>();

        for (String s : list) {
            if (predicate.test(s)) {
                list1.add(s);
            }

        }
        return list1;
    }
}
