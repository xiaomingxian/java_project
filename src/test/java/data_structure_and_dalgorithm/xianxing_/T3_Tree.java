package data_structure_and_dalgorithm.xianxing_;

import data_structure_and_dalgorithm.pojo.BinaryArray;
import data_structure_and_dalgorithm.pojo.BinaryTree;
import data_structure_and_dalgorithm.pojo.TreeNode;
import data_structure_and_dalgorithm.pojo.XianSuoTreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class T3_Tree {

    private static BinaryTree binaryTree;

    static {
        TreeNode root = new TreeNode(1);

        binaryTree = new BinaryTree(root);

        TreeNode leftNode = new TreeNode(2);
        TreeNode leftNode_l = new TreeNode(3);
        TreeNode leftNode_r = new TreeNode(5);
        leftNode.setLeftNode(leftNode_l);
        leftNode.setRightNode(leftNode_r);
        TreeNode rightNode = new TreeNode(4);
        TreeNode rightNode_l = new TreeNode(7);
        TreeNode rightNode_r = new TreeNode(8);
        rightNode.setLeftNode(rightNode_l);
        rightNode.setRightNode(rightNode_r);
        root.setLeftNode(leftNode);
        root.setRightNode(rightNode);
    }

    //    -------------------------------------------------- 遍历 ------------------

    /**
     * 前序遍历
     * 跟节点   左节点  右节点
     */
    @Test
    public void frontBinary() {
        binaryTree.frontBinary(binaryTree.getRoot());
    }

    /**
     * 中序遍历
     * 左节点   跟节点   右节点
     */
    @Test
    public void midBinary() {
        binaryTree.midBinary(binaryTree.getRoot());
    }
    /**
     * 后续遍历
     */
    @Test
    public  void afterBinary(){
        binaryTree.afterBinary(binaryTree.getRoot());
    }

    //    -------------------------------------------------- 查找 ------------------

    /**
     * 先序查找
     * 中序，后续同理
     */
    @Test
    public void frontSearch() {
        TreeNode treeNode = binaryTree.frontSearch(binaryTree.getRoot(), 100);
        System.out.println(treeNode);
    }

    //    -------------------------------------------------- 删除子树 ------------------
    @Test
    public void frontDelete() {
        TreeNode root = binaryTree.getRoot();
        root.delete(2);
        binaryTree.frontBinary(root);

    }

    //    ------------------------------------ 顺序存储二叉树  ------------------------

    /**
     * 前序遍历
     */
    @Test
    public void shunxuShow() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        BinaryArray binaryArray = new BinaryArray(arr);
        binaryArray.showAll(2);
    }

    //    -------------------------- 堆排序  ---------------------

    /**
     * 将数组排序为大顶堆
     */
    @Test
    public void bigHeadHeap() {
        int[] arr = {1, 4, 7, 2, 6, 3, 0};
        //    从最后一个非叶子节点-->跟节点
        int end = (arr.length - 1) / 2;
        for (int start = end; start >= 0; start--) {
            bigHeadSort(arr, arr.length, start);
        }

        System.out.println(Arrays.toString(arr));
        //   进行排序操作
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //    再把之前的变为大顶堆---为啥是0--因为以下部分已经是大顶堆了，只有0索引是变化的？但是为用最后的非叶子节点不行？
            bigHeadSort(arr, i, 0);
        }
        System.out.println("排序后：" + Arrays.toString(arr));


    }

    /**
     * @param arr
     * @param size  数组长度用于判断越界
     * @param index 被比较的节点【父节点】
     */
    public void bigHeadSort(int[] arr, int size, int index) {
        //    左节点
        int left = index * 2 + 1;
        //    右节点
        int right = index * 2 + 2;
        //    最大值临时记录为父节点
        int max = index;
        //    判断左节点是否越界和比较大小
        if (left < size && arr[left] > arr[max]) {
            max = left;
        }
        //    判断左节点是否越界和比较大小
        if (right < size && arr[right] > arr[max]) {
            max = right;
        }
        //   比较出最大值后进行交换
        if (max != index) {
            //    最大值与父节交换
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;
            //    比较出最大值后再将  最小的最为跟节点与下一节点进行比较
            bigHeadSort(arr, size, max);

        }
    }

    /**
     * 二叉树 线索化
     */
    @Test
    public void  xianSuoShu(){
        XianSuoTreeNode root = new XianSuoTreeNode(0);
        XianSuoTreeNode l1 = new XianSuoTreeNode(1);
        XianSuoTreeNode l2 = new XianSuoTreeNode(2);
        XianSuoTreeNode l3 = new XianSuoTreeNode(3);
        XianSuoTreeNode l4 = new XianSuoTreeNode(4);
        XianSuoTreeNode r1 = new XianSuoTreeNode(5);
        XianSuoTreeNode r2 = new XianSuoTreeNode(6);
        XianSuoTreeNode r3 = new XianSuoTreeNode(7);
        XianSuoTreeNode r4 = new XianSuoTreeNode(8);
        root.setLeftNode(l1);
        root.setRightNode(r1);

        r1.setLeftNode(l4);
        r1.setRightNode(r4);

        l1.setLeftNode(l2);
        l1.setRightNode(r2);

        l2.setLeftNode(l3);
        l2.setRightNode(r3);


        BinaryTree binaryTree = new BinaryTree(root);

        binaryTree.toBeXianSuoShu();
        //
        System.out.println(l3.getLeftType()+"----"+l3.getLeftNode());
        System.out.println(r3.getRightType()+"----"+r3.getRightNode());
        System.out.println(r4.getLeftType()+"----"+r4.getLeftNode());
        System.out.println(r4.getRightType()+"----"+r4.getRightNode());

        //    遍历线索二叉树
        binaryTree.xiansuoShow();
    }

    /**
     * 赫夫曼树【树的带权路径最小---权越小的离跟节点越远】
     */
    @Test
    public void hefumanTest() {
        int[] x = {3, 7, 8, 29, 5, 11, 23, 14};
        createHeFuMan(x);


    }

    public void createHeFuMan(int[] arr) {
        //    先以没个元素为跟节点创建一个单节点树【为了与其他节点连接起来(通过左右节点)】
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for (int i : arr) {
            TreeNode treeNode = new TreeNode(i);
            treeNodes.add(treeNode);
        }
        //    以最小的两个节点为叶子节点-创建一个树，然后从原始数组中移除这2个元素，再将父节[子节点权值之和]点作为比较元素添加原始数组中
        //    元素不断移除-知道最后数量为1时候就不比较
        while (treeNodes.size() > 1) {
            //集合有序化
            Collections.sort(treeNodes);
            //得到2个最小节点
            TreeNode left = treeNodes.get(treeNodes.size() - 2);
            TreeNode right = treeNodes.get(treeNodes.size() - 1);
            //    组成新节点
            TreeNode parent = new TreeNode(left.getValue() + right.getValue());
            parent.setLeftNode(left);
            parent.setRightNode(right);
            //    将最小节点从原始集合中移除
            treeNodes.remove(left);
            treeNodes.remove(right);
            //    将新节点添加至原始集合
            treeNodes.add(parent);
        }

        System.out.println(treeNodes);
    }

}
