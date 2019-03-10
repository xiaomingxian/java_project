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
        if (root.getLeftNode() != null) {
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
}
