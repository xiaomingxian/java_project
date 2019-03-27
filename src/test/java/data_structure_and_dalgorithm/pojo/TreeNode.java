package data_structure_and_dalgorithm.pojo;

public class TreeNode implements Comparable<TreeNode>{

    private Integer value;

    public TreeNode(Integer value) {
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

    public Integer getValue() {
        return value;
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

    /**
     * 先序删除
     */
    public void delete(int i) {
        TreeNode parentNode = this;

        //   先判断左，右
        if (null != parentNode.getLeftNode() && parentNode.getLeftNode().getValue() == i) {
            parentNode.leftNode = null;
            return;

        }
        //   先判断左，右
        if (null != parentNode.getRightNode() && parentNode.getRightNode().getValue() == 2) {
            parentNode.rightNode = null;
            return;

        }

        //    如果都不为符合-就把子节点当作跟节点进行递归
        parentNode = leftNode;
        if (null != parentNode) {
            delete(i);
        }

        parentNode = rightNode;
        if (null != parentNode) {
            delete(i);
        }
    }


    @Override
    public int compareTo(TreeNode o) {

        return this.value- o.value;
    }
}
