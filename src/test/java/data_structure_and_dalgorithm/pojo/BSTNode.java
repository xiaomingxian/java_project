package data_structure_and_dalgorithm.pojo;

public class BSTNode {
    private Integer value;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(Integer value) {
        this.value = value;
    }

    /**
     * add
     */
    public void add(BSTNode node) {
        if (value > node.value) {
            if (left == null) {
                left = node;
            } else {
                left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
            } else {
                right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public void midShow(BSTNode node) {
        if (node == null) {
            return;
        }
        midShow(node.left);
        System.out.println(node.value);
        midShow(node.right);
    }

    @Override
    public String toString() {
        return value + ",";
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
