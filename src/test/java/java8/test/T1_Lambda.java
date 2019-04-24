package java8.test;

import java8.pojo.Number;
import java8.pojo.Personl;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

public class T1_Lambda {
    /**
     * lambdaTest
     *
     * 1.->
     * 左侧参数列表  右侧lambda体
     * 2.
     * 3.
     * 4.
     * 5.类型推断--参数列表可以不写类型，jvm会根据上下文进行推断
     * 6.函数式接口
     */


    /**
     * 1.无参数
     */
    @Test
    public void wucan() {
        int num = 1;//1.7中匿名函数使用局部变量得用final修饰 1.8中不用但是本质还是一样，不能做改变eg:++,--会编译出错
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("原始" + num);
            }
        }).start();
        //************** 8
        new Thread(() -> System.out.println("8匿名" + num)).start();
    }

    /**
     * 2.但一个参数无返回值
     * 只有一个参数()可以省略
     */
    @Test
    public void ycwf() {
        Consumer con = (x) -> System.out.println(x);
        con.accept("aaa");//acept为对应的方法名
    }

    /**
     * 3.多参数，多语句{},有返回值
     */
    @Test
    public void more() {

        Comparator<Integer> c = (x, y) -> {
            System.out.println("语句1");
            return Integer.compare(x, y);
        };

    }

    /**
     * 4.如果lambda体中只有一条返回值语句----return和{}都可省略
     */
    @Test
    public void oneRet() {
        Comparator<Integer> c = (x, y) -> Integer.compare(x, y);
    }

    /**
     * 5.类型推断
     */
    @Test
    public void tuidaun() {
        show(new HashMap<>());//1.7中需指定类型

        Arrays.asList("aa", 1, true);//1.7中也有类型推断，1.8中对它做了增强
    }

    private void show(HashMap<String, String> map) {
    }


    /**
     * 函数式接口
     */
    @Test
    public void hanshu() {
        System.out.println(getNum(10, x -> x + 10));//10在getNum中作为number.getVal()的参数 10+10
    }

    private int getNum(int i, Number number) {
        return number.getVal(i);
    }

    //************************************** other test
    @Test
    public void clooectionTest() {
        List<Personl> people = Arrays.asList(
                new Personl("tom", 8, 10000),
                new Personl("jerry", 18, 15000),
                new Personl("cind", 8, 17000),
                new Personl("king", 22, 19000)
        );

        Collections.sort(people, (x, y) -> {
            if (x.getAge() > y.getAge()) return 1;
            else return -1;
        });

        System.out.println(people);


    }

}
