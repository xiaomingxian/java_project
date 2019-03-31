package data_structure_and_dalgorithm.pojo;

public class HefumanTreeNode implements Comparable<HefumanTreeNode>{

    private Byte b_value;//单词对应的 ACSCII码，因为只有叶子节点有 ACSCII码值，所以用包装类，允许为空
    private Integer value;//单词【byte值】 出现的次数

    private HefumanTreeNode left;
    private HefumanTreeNode right;

    @Override
    public String toString() {
        return "byte:"+b_value+"次数："+value;
    }

    public HefumanTreeNode(Byte b_value, Integer value) {
        this.b_value = b_value;
        this.value = value;

    }


    public Byte getB_value() {
        return b_value;
    }

    public void setB_value(Byte b_value) {
        this.b_value = b_value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public HefumanTreeNode getLeft() {
        return left;
    }

    public void setLeft(HefumanTreeNode left) {
        this.left = left;
    }

    public HefumanTreeNode getRight() {
        return right;
    }

    public void setRight(HefumanTreeNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HefumanTreeNode o) {
        //以出现的次数进行排序
        return o.value-this.value;
    }
}
