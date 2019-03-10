package data_structure_and_dalgorithm.pojo;

public class TreeNode {

    private String value;

    public TreeNode(String value) {
        this.value = value;
    }

    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "权：" + value;
    }
}
