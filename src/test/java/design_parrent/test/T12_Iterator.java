package design_parrent.test;

import design_parrent.pojo.p12_iterator.MyIter;
import design_parrent.pojo.p12_iterator.MyList;
import org.junit.Test;

public class T12_Iterator {
    /**
     * 迭代器
     * //list 中也是用的内部类----可以直接使用成员变量
     */
    @Test
    public void t1() {

        MyList list = new MyList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");


        MyIter iter = list.getIter();


        System.out.println("第一个元素：" + iter.first() + " isFirst:" + iter.isFirst() + " isLast:" + iter.isLast());
        System.out.println("last元素：" + iter.last());

        //移除元素
        iter.remove();

        System.out.println("========遍历");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
