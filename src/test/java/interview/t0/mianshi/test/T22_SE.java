package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class T22_SE {

    public static void main(String[] args) {
        System.out.println(2 >> 2);//  /
        System.out.println(2 << 2);//  *

        //     并发集合
        ConcurrentHashMap map = null;
        map.put(null, null);// 不允许null k/v   if (key == null || value == null) throw new NullPointerException();


        HashSet hashSet=null;


    }


    /**
     * 迭代删除
     */
    @Test
    public void listDele() {
        //1.常规删除方式-并发修改异常

        //asListError();


        //正序
        //method2();

        //倒序
        method3();

        //使用迭代器删除[不能使用集合删除，在使用迭代器遍历的同时不能改变集合(通过集合删除/添加元素)[会报并发修改异常(java认为这是线程不安全的操作)]，因为迭代器是通过集合获取]
        //iterDel1();
    }

    private void method3() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            if (s.equals("3")) {
                list.remove(i);
            }
        }

        System.out.println("删除后的集合元素：" + list);

    }

    private void iterDel1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            System.out.println("元素：" + next);
            iterator.remove();
        }
        System.out.println("删除后的集合：" + list);
    }

    private void method2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");


        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println("元素：" + s);
            if (s.equals("3")) {
                list.remove(s);
                if (i != 0)
                    i--;
            }
        }
        System.out.println(list);


    }

    private void asListError() {
        String[] strs = {"1", "2", "3", "4"};
        //此种转换不支持 删除   java.lang.UnsupportedOperationException
        List<String> list = Arrays.asList(strs);

        String remove = list.remove(1);
        System.out.println(remove);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            list.remove(i);
            if (i > 0)
                i--;
        }
    }


}
