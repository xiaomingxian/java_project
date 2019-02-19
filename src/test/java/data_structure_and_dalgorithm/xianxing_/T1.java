package data_structure_and_dalgorithm.xianxing_;

import data_structure_and_dalgorithm.pojo.Node;
import org.junit.Test;

public class T1 {

    /**
     * 数组：略
     * ArryList的底层时数组 增删改查略 增加与删除需要new 一个新数组 改变引用【所以增删不方便】
     */

    /**
     * 栈 略
     * 用数组模拟
     * push   pop
     *
     */


    /**
     * 队列  略
     * 先进先出
     */
    /**
     * 链表【单向】
     */
    @Test
    public void t1() {
        Node n1 = new Node("节点1");
        Node n2 = new Node("节点2");
        Node n3 = new Node("节点3");
        Node n4 = new Node("节点4");
        //method1:
        n1.appendNext(n1,n2);
        n1.appendNext(n1,n2);
        n1.appendNext(n1,n3);
        n1.appendNext(n1,n4);

        //method2:
        //n1.appendNextSingle(n2);
        //n1.appendNextSingle(n3);
        //n1.appendNextSingle(n3);
        //n1.appendNextSingle(n4);

        //method3:
        //n1.appendNextLian(n2)
        //        .appendNextLian(n3)
        //        .appendNextLian(n3)
        //        .appendNextLian(n4);

        System.out.println(n1.getNext());
        System.out.println(n2.getNext());
        System.out.println(n3.getNext());


    }
}
