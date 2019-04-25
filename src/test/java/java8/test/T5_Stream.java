package java8.test;

import org.junit.Test;
import pojo.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class T5_Stream {

    static List<Person> list;

    static {
        list = Arrays.asList(
                new Person("tom", "1"),
                new Person("jerry", "1"),
                new Person("cindy", "0"),
                new Person("alice", "0"),
                new Person("alice", "0"),
                new Person("alice", "0")

        );
    }

    /**
     * 创建流的四种方式
     */
    @Test
    public void te() {
        //1.可以通过Collection系列的集合的 stream() 或 parallelStream()[并行流]
        ArrayList<String> a = new ArrayList<>();
        Stream<String> stream = a.stream();

        //2.通过 Arrays 中的静态方法 stream() 获取数组流
        String[] strs = new String[8];
        Stream<String> stream1 = Arrays.stream(strs);


        //3.通过stream中的静态方法
        Stream<? extends Serializable> stream2 = Stream.of("", "", true, 1, 10.9);

        //4.创建无限流
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);//起始参数，函数
        //iterate.forEach(System.out::println);
        iterate.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> (int) (Math.random() * 10) + 1).limit(10).forEach(System.out::println);

    }

    /**
     * 中间操作
     * filter 从流中排除某些元素
     * limit  截断流，使元素不超过给定数量，具有短路效果
     * skip   跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
     * distinct 删选，通过流生成的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void mid() {
        //filter
        list.stream().filter((e) -> e.getSex().equals("1")).forEach(System.out::println);//forech属于终止操作---惰性求值
        System.out.println("-------------------");
        //distinct
        list.stream().distinct().forEach(System.out::println);//去重操作
        System.out.println("-------------------");
        //skip
        list.stream().skip(4).forEach(System.out::println);//skip
    }

    /**
     * map: 映射
     * flatmap 接受一个函数作为参数，将流中的每一个值换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void mapTest() {

        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc");
        //提取+转换--->原容器元素不发生变化
        list1.stream().map((x) -> x.toUpperCase()).forEach(System.out::println);
        System.out.println(list1);
        System.out.println("-------------");
        list.stream().map(Person::getName).forEach(System.out::println);
        System.out.println("--------------");
        Stream<Character> characterStream = list1.stream().flatMap((x) -> T5_Stream.getChar(x));//集合有三个元素，得到三个流，flatMap将三个流转成一个流
        characterStream.forEach(System.out::println);

    }

    static Stream<Character> getChar(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}
