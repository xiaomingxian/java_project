package data_structure_and_dalgorithm.pojo;

public class BSTree {
    private BSTNode root;

    public void add(BSTNode bstNode) {
        if (this.root == null) {
            this.root = bstNode;
        } else {
            this.root.add(bstNode);
        }
    }

    /**
     * 中序遍历
     */
    public void midShow() {
        root.midShow(root);
    }


}
