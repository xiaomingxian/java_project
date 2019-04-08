package data_structure_and_dalgorithm.pojo;

public class AVLTree {

    private AVLNode root;

    public AVLTree() {
    }

    /**
     * 添加节点
     *
     * @return
     */
    public void add(AVLNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void midShow() {
        root.midShow(root);
    }

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }
}
