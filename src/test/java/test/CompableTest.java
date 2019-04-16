package test;

import pojo.Person;
import pojo.Test;

import java.util.*;

public class CompableTest {
    public static void main(String[] args) {
        Test test1 = new Test(0);
        Test test2 = new Test(2);
        Test test3 = new Test(5);
        Test test4 = new Test(1);

        Set<Object> list = new TreeSet<>();
        list.add(test1);
        list.add(test2);
        list.add(test3);
        list.add(test4);

        System.out.println(list);
    }

    /**
     * 得实现Compable接口
     */
    @org.junit.Test
    public void comparableTest() {
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        Person p4 = new Person();
        Person p5 = new Person();
        ArrayList list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        Collections.sort(list);

    }
}
