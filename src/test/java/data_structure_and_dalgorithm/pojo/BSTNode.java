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

    /**
     * serach
     *
     * @return
     */
    public BSTNode search(Integer value) {
        BSTNode i = null;
        if (this.value == value) {
            i = this;
        } else if (value < this.value) {
            //    查询左树
            if (left == null) {
                return null;
            } else {
                i = left.search(value);
            }
        } else {
            //    查询右树
            if (right == null) {
                return null;
            } else {
                i = right.search(value);
            }
        }

        return i;

    }

    /**
     * 查找父节点--从跟节点开始遍历查找
     */
    public BSTNode searchParent(Integer value) {
        //因为用的是跟节点调用
        if (this.value == value) {
            return this;
        }
        if ((this.right != null && this.right.value == value) || (this.left != null && this.left.value == value)) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.searchParent(value);
            } else {
                return null;
            }
        } else if (value > this.value) {
            if (this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }

        }
        //当前节点可能是跟节点
        return null;
    }

    /**
     * 查找最左节点
     *
     * @return
     */
    public BSTNode leftNode(BSTNode bstNode) {

        BSTNode left = bstNode.getLeft();
        if (left == null) {
            return bstNode;
        } else {
            return leftNode(left);
        }
    }

    /**
     * 查找最右节点
     *
     * @return
     */
    public BSTNode rightNode(BSTNode bstNode) {

        BSTNode left = bstNode.getRight();
        if (left == null) {
            return bstNode;
        } else {
            return rightNode(left);
        }
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
