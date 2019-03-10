package data_structure_and_dalgorithm.xianxing_;

import data_structure_and_dalgorithm.pojo.BinaryTree;
import data_structure_and_dalgorithm.pojo.TreeNode;
import org.junit.Test;

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


}
