package data_structure_and_dalgorithm.pojo;

public class AVLNode {
    private Integer value;
    private AVLNode left;
    private AVLNode right;

    public AVLNode() {
    }

    public AVLNode(Integer i) {
        this.value = i;
    }

    /**
     * 树的高度--递归查找
     *
     * @return
     */
    public int height() {
        //最高子树的高度+1
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public int leftH() {
      return   this.left == null ? 1 : left.height();
    }

    public int rightH() {
        return   this.right == null ? 1 : right.height();
    }

    /**
     * 添加节点
     *
     * @return
     */
    public void add(AVLNode node) {
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
        //判断是否平衡
        if (leftH() - rightH() > 1) {
            //判断左子树的左边与右边的高度差-双旋转
            if (left.leftH() < left.rightH()) {
                //右旋转---将跟树接在右树的左子节点上
                left.roll(left, 1);
            }
            //    进行左旋转---只进行这一步就是单旋转
            roll(this, 0);

        } else if ( rightH() - leftH() > 1) {
            if (right.rightH() < right.leftH()) {
                //左旋转--将跟树接在左树的右子节点上
                right.roll(right, 0);
            }
            //进行右旋转
            roll(this, 1);

        }
    }

    private void roll(AVLNode current, int i) {
        if (i == 0) {
            //    左旋转-将右子树接到左子树的右节点上--如果右节点有右子节点，就将右子节点作为当前节点的左子节点【因为当前节点大于它的前驱节点】
            //左子节点的右子节点无论是不是null 都将它作为 current的左子节点
            //将当前节点的值变为左子节点的值，将左子节点的左子节点变为当前节点的左子节点【因为当前节点的值与左子节点的值相同了】
            //当前节点的右子节点
            AVLNode avlNode = new AVLNode(current.value);
            avlNode.setLeft(current.getLeft());
            avlNode.setRight(current.getRight());
            //当前节点的左子节点
            AVLNode left = current.left;
            //左子节点的右子节点
            AVLNode right1 = left.getRight();
            //左子节点的左子节点
            AVLNode left1 = left.getLeft();

            //将左子节点值赋给当前节点
            current.setValue(left.getValue());
            current.setLeft(left1);
            avlNode.setLeft(right1);
            current.setRight(avlNode);

        } else {
            //    右旋转---将当前节点的左子节点移动到右子节点的左子节点上--原来右子节点的左子节点作为移过来的节点的右子节点--当前节点的值为右子节点的值-删除右子节点
            //    当前节点的左子节点
            AVLNode avlNode = new AVLNode(current.value);
            avlNode.setLeft(current.getLeft());
            avlNode.setRight(current.getRight());
            //     当前节点的右子节点
            AVLNode current1 = current.right;

            //    当前节点的右子节点的左子节点
            AVLNode left1 = current1.left;
            //    右子节点的右子节点
            AVLNode right1 = current1.getRight();

            avlNode.setRight(left1);
            current.setValue(current1.value);
            current.setRight(right1);
            current.setLeft(avlNode);

        }

    }

    public void midShow(AVLNode node) {
        if (node == null) {
            return;
        }
        midShow(node.left);
        System.out.println(node.value);
        midShow(node.right);
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }
}
