package java8.test;

import org.junit.Test;
import pojo.Person;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T5_Stream {

    static List<Person> list;

    static {
        list = Arrays.asList(
                new Person("tom", "1"),
                new Person("jerry", "0"),
                new Person("cindy", "0"),
                new Person("alice", "0"),
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

    /**
     * 排序
     */
    @Test
    public void muSort() {
        list.stream().sorted(
                (x, y) -> {
                    return x.getName().compareTo(y.getName());
                }
        ).forEach(System.out::println);
    }

    /**
     * 查找与匹配
     */
    @Test
    public void findAndMatch() {
        //匹配所有
        boolean b = list.stream().allMatch((x) -> x.getSex().equals("1"));
        System.out.println(b);
        //匹配一个至少
        boolean b1 = list.stream().anyMatch((x) -> x.getSex().equals("1"));
        System.out.println(b1);
        //没有匹配元素
        boolean b2 = list.stream().noneMatch((x) -> x.equals("2"));
        System.out.println(b2);

        System.out.println("------------------------------");
        //    查找
        Optional<Person> first = list.stream().filter((x) -> x.getSex().equals("3")).findFirst();
        //System.out.println(first.get());
        first.orElse(new Person("unknow", "3"));//空值替代--未验证成功

        //    随便找一个    parallelStream并行查找
        Optional<Person> any = list.parallelStream().findAny();
        System.out.println(any.get());

        //    返回总个数
        long count = list.parallelStream().count();
        System.out.println(count);


        //    最大值
        Optional<Person> max = list.stream().max((x, y) -> {
            return -x.getName().compareTo(y.getName());
        });

        //提取出某一个字段
        Optional<String> max1 = list.stream().map(Person::getName).max(String::compareTo);
        System.out.println(max1.get());

        System.out.println(max.get());
        //    最小值
        Optional<Person> min = list.stream().min((x, y) -> {
            return -x.getName().compareTo(y.getName());
        });
        System.out.println(min.get());

    }

    /**
     * 规约
     * 可以将流中的元素反复结合起来，得到一个新值
     */
    @Test
    public void guiY() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        Integer reduce = list1.stream().reduce(2, (x, y) -> x + y);//起始值  (起始之，集合中的值)->运算方式--->运算出的值再次作为起始值
        System.out.println(reduce);

        Optional<String> reduce1 = list.stream().map(Person::getName).reduce(String::join);
        System.out.println(reduce1.get());
    }

    /**
     * 收集：提取某个字段放到容器中
     */
    @Test
    public void coll() {
        //Collectors是一个工具类
        List<String> l = list.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(l);
        //l.forEach(System.out::println);

        //收集到set去重
        Set<String> collect = list.stream().map(Person::getName).collect(Collectors.toSet());
        System.out.println(collect);
        //其他容器
        Set<String> other = list.stream().map(Person::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(other);

        //    其他api
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5, 6);
        Long collect1 = in.stream().collect(Collectors.counting());
        System.out.println("总数：" + collect1);

        Double collect2 = in.stream().collect(Collectors.averagingDouble(Integer::intValue));
        System.out.println("平均值：" + collect2);

        Long collect3 = in.stream().collect(Collectors.summingLong(Integer::intValue));
        System.out.println("求和：" + collect3);
        //分组
        Map<String, List<Person>> collect4 = list.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println(collect4);

        //多条件分组
        Map<String, Map<String, List<Person>>> collect5 = list.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getName)));
        System.out.println(collect5);

        //分区
        Map<Boolean, List<Integer>> collect6 = in.stream().collect(Collectors.partitioningBy((x) -> x > 3));
        System.out.println("分区：" + collect6);

        Map<Boolean, List<Person>> collect7 = list.stream().collect(Collectors.partitioningBy((x) -> x.getSex().equals(0)));
        System.out.println("对象分区：" + collect7);

        //数值计算综合
        DoubleSummaryStatistics collect8 = in.stream().collect(Collectors.summarizingDouble(Integer::intValue));
        System.out.println("---------综合数值计算----------");
        System.out.println(collect8.getMax());
        System.out.println(collect8.getAverage());
        System.out.println(collect8.getCount());

        //字符串拼接
        List<String> ss = Arrays.asList("s", "c", "x");
        String collect9 = ss.stream().collect(Collectors.joining(",", "-", "="));//分隔符，前缀，后缀
        System.out.println(collect9);


    }
}
