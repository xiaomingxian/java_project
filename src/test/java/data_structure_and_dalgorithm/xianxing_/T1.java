package data_structure_and_dalgorithm.xianxing_;

import data_structure_and_dalgorithm.pojo.Node;
import data_structure_and_dalgorithm.pojo.NodeDouble;
import data_structure_and_dalgorithm.pojo.NodeLop;
import org.junit.Test;

import java.io.File;

public class T1 {
    //---------------------------- 顺序存储  -----------------
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
    //--------------------------------------------

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
        n1.appendNext(n1, n2);
        n1.appendNext(n1, n2);
        n1.appendNext(n1, n3);
        n1.appendNext(n1, n4);

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

        //    删除节点 略【只能根据当前节点删除下一个节点】因为是单链表无法获取上一个节点的地址
        //    插入节点 略
    }

    /**
     * 循环链表
     */
    @Test
    public void xunhuan() {
        NodeLop n1 = new NodeLop("1");
        NodeLop n2 = new NodeLop("2");
        NodeLop n3 = new NodeLop("3");

        n1.append(n2).append(n3);
        //n2.append(n3);

        System.out.println(n1.getNext());
        System.out.println(n2.getNext());
        System.out.println(n3.getNext());


    }

    /**
     * 双向循环
     */
    @Test
    public void nodeDouble() {
        NodeDouble n1 = new NodeDouble("1");
        NodeDouble n2 = new NodeDouble("2");
        NodeDouble n3 = new NodeDouble("3");
        NodeDouble n4 = new NodeDouble("4");
        n1.append(n2).append(n3).append(n4);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
    }

    /**
     * 递归--递归复制--递归删除
     */
    @Test
    public void digui() {
        hanNoTa(3, "左边", "中间", "右边");

    }

    /**
     * 最少步骤汉诺塔问题
     * 将所有情况分为只有一个圈和两个圈【最下面的是一个，上面的整体看为一个】
     *
     * @param n
     * @param L
     * @param M
     * @param R
     */
    private void hanNoTa(int n, String L, String M, String R) {
        if (n == 1) {
            System.out.println("将第1个圈从[" + L + "]移到[" + R + "]");
        } else {
            //如果数量大于一,先将上面所有的移动到中间，
            hanNoTa(n - 1, L, R, M);
            // 再将最后一个移动到目标位置
            System.out.println("将第" + n + "个圈从[" + L + "]移到[" + R + "]");
            //    再将上面的移动到目标位置
            hanNoTa(n - 1, M, L, R);

        }
    }

    private void copy(File file) {
        //    判断是文件还是文件夹

    }

}
