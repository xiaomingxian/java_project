package data_structure_and_dalgorithm.pojo;

public class XianSuoTreeNode extends TreeNode {
    //增加左右节点的类型描述---默认值为0表示为左子节点或右子节点
    int leftType;
    int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public XianSuoTreeNode(Integer value) {
        super(value);
    }




}
