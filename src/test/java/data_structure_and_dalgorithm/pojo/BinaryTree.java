package data_structure_and_dalgorithm.pojo;

public class BinaryTree {

    private TreeNode root;

    public BinaryTree(TreeNode treeNode) {
        this.root = treeNode;
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void frontBinary(TreeNode root) {
        System.out.println(root);
        if ( null!=root.getLeftNode()) {
            //
            frontBinary(root.getLeftNode());
        }
        if (null != root.getRightNode()) {
            frontBinary(root.getRightNode());
        }
    }


    public TreeNode getRoot() {
        return root;
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void midBinary(TreeNode root) {
        if (null != root.getLeftNode()) {
            midBinary(root.getLeftNode());
        }

        System.out.println(root);
        if (null != root.getRightNode()) {
            midBinary(root.getRightNode());
        }

    }

    /**
     * 后续遍历
     *
     * @param root
     */
    public void afterBinary(TreeNode root) {

        if (null != root.getLeftNode()) {
            afterBinary(root.getLeftNode());
        }
        if (null != root.getRightNode()) {
            afterBinary(root.getRightNode());
        }
        System.out.println(root);
    }

    /**
     * 先序查找
     *
     * @param i
     * @return
     */
    public TreeNode frontSearch(TreeNode root, int i) {
        TreeNode treeNode = null;
        if (root.getValue() == i) {
            return root;
        }
        if (null != root.getLeftNode()) {
            treeNode = frontSearch(root.getLeftNode(), i);
        }
        if (treeNode != null) {
            return treeNode;
        }
        if (null != root.getRightNode()) {
            treeNode = frontSearch(root.getRightNode(), i);
        }
        return treeNode;
    }


    public void toBeXianSuoShu() {
        try {
            getXianSuoShu(root);

        } catch (Exception e) {
            System.out.println("当前树不可线索化");

        }
    }


    //记录前一个节点
    private TreeNode pre;

    /**
     * 二叉树线索化
     *
     * @param treeNode
     */
    //使得树成为线索二叉树[中序]  ----  左 中 右
    public void getXianSuoShu(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }

        //对左树[节点]线索化
        getXianSuoShu(treeNode.getLeftNode());
        //对中间树线索化【对自己线索化】
        //处理前驱节点---如果没有左节点--就指向前驱节点
        if (null == treeNode.getLeftNode()) {
            XianSuoTreeNode xiansuo = (XianSuoTreeNode) treeNode;
            xiansuo.setLeftNode(pre);
            xiansuo.setLeftType(1);
        }
        //处理上一节点的后驱节点
        if (null != pre && pre.getRightNode() == null) {
            XianSuoTreeNode xiansuo = (XianSuoTreeNode) pre;
            xiansuo.setRightType(1);
            xiansuo.setRightNode(treeNode);
        }

        //将当前节点赋值给上一节点
        pre = treeNode;

        //    对右树线索化
        getXianSuoShu(treeNode.getRightNode());

    }


    /**
     * 线索化二叉树遍历
     * 找到最左节点开始遍历，
     */
    public void xiansuoShow() {
        XianSuoTreeNode xianSuoTreeNode = (XianSuoTreeNode) root;
        while (xianSuoTreeNode != null) {
            //    找到最左节点 type==0说明存在左子节点--当type!=0时说明没有左子节点，即到了最左
            while (xianSuoTreeNode.leftType == 0) {
                xianSuoTreeNode = (XianSuoTreeNode) xianSuoTreeNode.getLeftNode();
            }
            //    开始向右遍历--先打印当前节点
            System.out.println(xianSuoTreeNode);
            while (xianSuoTreeNode.rightType == 1) {//说明到了最后一个节点
                System.out.println(xianSuoTreeNode.getRightNode());
                //这一步是为了跳出循环
                xianSuoTreeNode = (XianSuoTreeNode) xianSuoTreeNode.getRightNode();
            }
            //System.out.println("--------->"+xianSuoTreeNode);
            //    将右节点或者后继节点记录起来继续循环
            xianSuoTreeNode = (XianSuoTreeNode) xianSuoTreeNode.getRightNode();
        }
    }
}
