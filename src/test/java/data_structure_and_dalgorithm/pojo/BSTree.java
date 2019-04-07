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

    /**
     * 查找
     */
    public BSTNode search(Integer value) {
        return root.search(value);
    }


    /**
     * 删除叶子节点
     */
    public void delYeZi(Integer value) {
        BSTNode bstNode = root.searchParent(value);
        if (bstNode == null) {
            System.out.println("此节点不存在");
        } else {
            if (bstNode.getLeft() != null && bstNode.getLeft().getValue() == value) {
                if (bstNode.getLeft().getLeft() == null && bstNode.getLeft().getRight() == null) {

                    bstNode.setLeft(null);
                } else {
                    System.out.println("当前节点不是叶子节点，不可删除");
                }
            } else {
                if (bstNode.getRight().getLeft() == null && bstNode.getRight().getRight() == null) {

                    bstNode.setRight(null);
                } else {
                    System.out.println("当前节点不是叶子节点，不可删除");
                }
            }
            System.out.println("-->父节点：" + bstNode);
        }

    }

    /**
     * 删除只有一个叶子节点的节点
     */
    public void delNodeHaveOne(Integer value) {
        BSTNode bstNode = root.searchParent(value);
        if (bstNode == null) {
            System.out.println("此节点不存在");
            return;
        } else {
            //左节点相等
            if (bstNode.getLeft() != null && bstNode.getLeft().getValue() == value) {
                //节点下只有左节点
                if (bstNode.getLeft().getLeft() != null && bstNode.getLeft().getRight() == null) {
                    bstNode.setLeft(bstNode.getLeft().getLeft());
                } else if (bstNode.getLeft().getRight() != null && bstNode.getLeft().getLeft() == null) {
                    //节点下只有右节点
                    bstNode.setLeft(bstNode.getLeft().getRight());
                } else {
                    System.out.println("--此节点不是 只有一个子节点的节点");
                    return;
                }

            } else {
                if (bstNode.getRight() != null && bstNode.getRight().getValue() == value) {
                    //节点下只有左节点
                    if (bstNode.getRight().getLeft() != null && bstNode.getRight().getRight() == null) {
                        bstNode.setRight(bstNode.getRight().getLeft());
                    } else if (bstNode.getLeft().getRight() != null && bstNode.getLeft().getLeft() == null) {
                        //节点下只有右节点
                        bstNode.setRight(bstNode.getRight().getRight());
                    } else {
                        System.out.println("--此节点不是 只有一个子节点的节点");
                        return;
                    }
                }
            }
            System.out.println("-->父节点：" + bstNode);
        }
    }

    /**
     * 删除有两个子节点的节点
     */
    public void delNodeHaveTwo(Integer value) {
        //判断此节点是否有两个子节点
        BSTNode search = root.search(value);
        if (null == search) {
            System.out.println("此节点不存在");
            return;
        } else if (!(search.getLeft() != null && search.getRight() != null)) {
            System.out.println("此节点不满足有两个子节点的要求");
            return;
        }
        BSTNode parentNode = root.searchParent(value);
        BSTNode currentNode = null;
        if (parentNode == null) {
            System.out.println("此节点不存在");
            return;
        } else {
            //判断当前节点是左边还是右边
            if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                currentNode = parentNode.getLeft();
                delTwo(currentNode, 0);
            } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {
                currentNode = parentNode.getRight();
                delTwo(currentNode, 0);
            } else if (root == parentNode) {
                currentNode = root;
                delTwo(currentNode, 0);
            }
        }
    }

    /**
     * 删除具有双节点的节点------
     * 约定为i==0替换当前节点为前驱节点
     * 约定为i==1替换当前节点为后驱节点
     *
     * @param currentNode
     */
    private void delTwo(BSTNode currentNode, int i) {
        //记录原节点的左右节点
        BSTNode left = currentNode.getLeft();
        BSTNode right = currentNode.getRight();
        if (i == 0) {
            left = null;
            //查找前驱节点
            BSTNode rightNode = currentNode.rightNode(currentNode.getLeft());
            //    删除此节点---如果此节点有左子节点
            BSTNode bstNode1 = rightNode.searchParent(rightNode.getValue());
            if(bstNode1.getLeft()!=null){

                delNodeHaveOne(rightNode.getValue());
            }else {

                bstNode1.setRight(null);
            }
            //    查找当前当前节点是否有父节点
            BSTNode parentNode = root.searchParent(currentNode.getValue());
            //前驱节点替换当前节点并添加子节点
            rightNode.setLeft(left);
            rightNode.setRight(right);
            System.out.println("----->" + rightNode);
            if (root == currentNode) {
                //    为跟节点
                root = rightNode;
            } else {
                //不为跟节点--删除原来节点-追加到父节点下
                Integer value = currentNode.getValue();
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(rightNode);
                } else {
                    parentNode.setRight(rightNode);
                }
            }

        } else {
            //同理--略
            right = null;
            //查找后驱节点
            BSTNode bstNode = root.rightNode(currentNode);

        }
    }

}
