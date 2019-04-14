package data_structure_and_dalgorithm.xianxing_;

import data_structure_and_dalgorithm.pojo.*;
import org.junit.Test;

import java.util.*;


public class T3_Tree {

    private static BinaryTree binaryTree;

    static {
        TreeNode root = new TreeNode(1);

        binaryTree = new BinaryTree(root);

        TreeNode leftNode = new TreeNode(2);
        TreeNode leftNode_l = new TreeNode(3);
        TreeNode leftNode_r = new TreeNode(5);
        leftNode.setLeftNode(leftNode_l);
        leftNode.setRightNode(leftNode_r);
        TreeNode rightNode = new TreeNode(4);
        TreeNode rightNode_l = new TreeNode(7);
        TreeNode rightNode_r = new TreeNode(8);
        rightNode.setLeftNode(rightNode_l);
        rightNode.setRightNode(rightNode_r);
        root.setLeftNode(leftNode);
        root.setRightNode(rightNode);
    }

    //    -------------------------------------------------- 遍历 ------------------

    /**
     * 前序遍历
     * 跟节点   左节点  右节点
     */
    @Test
    public void frontBinary() {
        binaryTree.frontBinary(binaryTree.getRoot());
    }

    /**
     * 中序遍历
     * 左节点   跟节点   右节点
     */
    @Test
    public void midBinary() {
        binaryTree.midBinary(binaryTree.getRoot());
    }
    /**
     * 后续遍历
     */
    @Test
    public  void afterBinary(){
        binaryTree.afterBinary(binaryTree.getRoot());
    }

    //    -------------------------------------------------- 查找 ------------------

    /**
     * 先序查找
     * 中序，后续同理
     */
    @Test
    public void frontSearch() {
        TreeNode treeNode = binaryTree.frontSearch(binaryTree.getRoot(), 100);
        System.out.println(treeNode);
    }

    //    -------------------------------------------------- 删除子树 ------------------
    @Test
    public void frontDelete() {
        TreeNode root = binaryTree.getRoot();
        root.delete(2);
        binaryTree.frontBinary(root);

    }

    //    ------------------------------------ 顺序存储二叉树  ------------------------

    /**
     * 前序遍历
     */
    @Test
    public void shunxuShow() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        BinaryArray binaryArray = new BinaryArray(arr);
        binaryArray.showAll(2);
    }

    //    -------------------------- 堆排序  ---------------------

    /**
     * 将数组排序为大顶堆
     */
    @Test
    public void bigHeadHeap() {
        int[] arr = {1, 4, 7, 2, 6, 3, 0};
        //    从最后一个非叶子节点-->跟节点
        int end = (arr.length - 1) / 2;
        for (int start = end; start >= 0; start--) {
            bigHeadSort(arr, arr.length, start);
        }

        System.out.println(Arrays.toString(arr));
        //   进行排序操作
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //    再把之前的变为大顶堆---为啥是0--因为以下部分已经是大顶堆了，只有0索引是变化的？但是为用最后的非叶子节点不行？
            bigHeadSort(arr, i, 0);
        }
        System.out.println("排序后：" + Arrays.toString(arr));


    }

    /**
     * @param arr
     * @param size  数组长度用于判断越界
     * @param index 被比较的节点【父节点】
     */
    public void bigHeadSort(int[] arr, int size, int index) {
        //    左节点
        int left = index * 2 + 1;
        //    右节点
        int right = index * 2 + 2;
        //    最大值临时记录为父节点
        int max = index;
        //    判断左节点是否越界和比较大小
        if (left < size && arr[left] > arr[max]) {
            max = left;
        }
        //    判断左节点是否越界和比较大小
        if (right < size && arr[right] > arr[max]) {
            max = right;
        }
        //   比较出最大值后进行交换
        if (max != index) {
            //    最大值与父节交换
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;
            //    比较出最大值后再将  最小的最为跟节点与下一节点进行比较
            bigHeadSort(arr, size, max);

        }
    }

    /**
     * 二叉树 线索化
     */
    @Test
    public void  xianSuoShu(){
        XianSuoTreeNode root = new XianSuoTreeNode(0);
        XianSuoTreeNode l1 = new XianSuoTreeNode(1);
        XianSuoTreeNode l2 = new XianSuoTreeNode(2);
        XianSuoTreeNode l3 = new XianSuoTreeNode(3);
        XianSuoTreeNode l4 = new XianSuoTreeNode(4);
        XianSuoTreeNode r1 = new XianSuoTreeNode(5);
        XianSuoTreeNode r2 = new XianSuoTreeNode(6);
        XianSuoTreeNode r3 = new XianSuoTreeNode(7);
        XianSuoTreeNode r4 = new XianSuoTreeNode(8);
        root.setLeftNode(l1);
        root.setRightNode(r1);

        r1.setLeftNode(l4);
        r1.setRightNode(r4);

        l1.setLeftNode(l2);
        l1.setRightNode(r2);

        l2.setLeftNode(l3);
        l2.setRightNode(r3);


        BinaryTree binaryTree = new BinaryTree(root);

        binaryTree.toBeXianSuoShu();
        //
        System.out.println(l3.getLeftType()+"----"+l3.getLeftNode());
        System.out.println(r3.getRightType()+"----"+r3.getRightNode());
        System.out.println(r4.getLeftType()+"----"+r4.getLeftNode());
        System.out.println(r4.getRightType()+"----"+r4.getRightNode());

        //    遍历线索二叉树
        binaryTree.xiansuoShow();
    }

    /**
     * 赫夫曼树【树的带权路径最小---权越小的离跟节点越远】
     */
    @Test
    public void hefumanTest() {
        int[] x = {3, 7, 8, 29, 5, 11, 23, 14};
        createHeFuMan(x);
    }

    public void createHeFuMan(int[] arr) {
        //    先以没个元素为跟节点创建一个单节点树【为了与其他节点连接起来(通过左右节点)】
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for (int i : arr) {
            TreeNode treeNode = new TreeNode(i);
            treeNodes.add(treeNode);
        }
        //    以最小的两个节点为叶子节点-创建一个树，然后从原始数组中移除这2个元素，再将父节[子节点权值之和]点作为比较元素添加原始数组中
        //    元素不断移除-知道最后数量为1时候就不比较
        while (treeNodes.size() > 1) {
            //集合有序化
            Collections.sort(treeNodes);
            //得到2个最小节点
            TreeNode left = treeNodes.get(treeNodes.size() - 2);
            TreeNode right = treeNodes.get(treeNodes.size() - 1);
            //    组成新节点
            TreeNode parent = new TreeNode(left.getValue() + right.getValue());
            parent.setLeftNode(left);
            parent.setRightNode(right);
            //    将最小节点从原始集合中移除
            treeNodes.remove(left);
            treeNodes.remove(right);
            //    将新节点添加至原始集合
            treeNodes.add(parent);
        }

        System.out.println(treeNodes);
    }

    /**
     * ----------------------------------------------赫夫曼编码,解码------------------------------------------------------------
     * 解压验证未通过：0的原因
     */
    //编码表---byte,路径
    Map mapTable = new HashMap<Byte, String>();

    @Test
    public void HeFuManEnCode() {
        String msg = "can you can a can as a can canner can a can.";
        byte[] bytes = msg.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(msg.substring(i, i + 1) + ":" + bytes[i] + ",");
        }
        byte[] bytes1 = myEncode(bytes);
        System.out.println(Arrays.toString(bytes1));
        String res = decodeHe(mapTable, bytes1);
    }

    private String decodeHe(Map mapTable, byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        //先将加密结果转化为二进制--计算机使用的是二进制的补码--正数不够用0补齐 【正数的原反补相同】--负数用1补齐
        for (int i = 0; i < bytes.length; i++) {
            //method1:
            //判断是否是最后一个--是就不取补码
            byte b = bytes[i];
            if (i == bytes.length - 1) {
                String s = Integer.toBinaryString(b);
                sb.append(s);
            } else {
                //小于0前面用0补齐---大于0截取后八位
                int temp = b;
                //补码操作----结果正数9位数【为何，去掉最高位正常】------负数为为32位截取后八位
                temp |= 256;
                //截取后八位
                String s = Integer.toBinaryString(temp);
                if (b < 0) {
                    //System.out.println("--->" + s + "  " + s.length());
                    //s = s.substring(s.length() - 8);
                } else {
                    //System.out.println("===>" + b + "  " + s);
                    //s=s.substring(s.length()-8);
                }
                s = s.substring(s.length() - 8);
                //System.out.println("====>"+s);
                sb.append(s);
            }
            //method2
            //if (b < 0) {
            //    //    截取后八位---因为不够会用1补齐，取byte8位所以要截取后八位
            //    String s = Integer.toBinaryString(b);
            //    String substring = s.substring(s.length() - 8);
            //    sb.append(substring);
            //} else {
            //        //    正数不够八位用0补齐
            //        String s = Integer.toBinaryString(b);
            //        if (s.length() < 8) {
            //            String pre = "";
            //            for (int i = 0; i < 8 - s.length(); i++) {
            //                pre += "0";
            //            }
            //            String zhengshu = pre + s;
            //            sb.append(zhengshu);
            //        }
            //
            //}
        }
        System.out.println("解码转二进制：" + sb);

        return "";
    }


    private byte[] myEncode(byte[] bytes) {
        //统计每个字符【对应的 ACSCII码】出现的次数
        Map map = myMount(bytes);
        //进行赫夫曼树化
        HefumanTreeNode hefumanTreeNode= hefuman(map);
        //创建赫夫曼编码表
        getCodeTable(hefumanTreeNode);
        System.out.println(mapTable);
        //    进行编码
        StringBuilder code = toZip(bytes, mapTable);
        //转为二进制？十进制？--为何
        byte[] bytes1 = to10JinZhi(code);
        return bytes1;

    }

    private byte[] to10JinZhi(StringBuilder code) {
        //返回byte数组，需要计算byte数组长度
        int len = code.length() % 8 == 0 ? code.length() / 8 : code.length() / 8 + 1;
        byte[] by = new byte[len];
        //记录索引
        int index = 0;
        //    8位一byte,进行截取
        for (int i = 0; i < code.length(); i += 8) {
            int lastIndex = i + 8;
            if (lastIndex >= code.length()) {
                lastIndex = code.length();
            }
            String s = (code + "").substring(i, lastIndex);
            //    转为十进制
            byte i1 = (byte) Integer.parseInt(s, 2);//2为二进制
            by[index] = i1;
            index++;
        }
        return by;
    }

    private StringBuilder toZip(byte[] bytes, Map mapTable) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            StringBuilder o = (StringBuilder) mapTable.get(b);
            sb.append(o);
        }
        System.out.println("编码且未压缩：" + sb);
        return sb;
    }

    //全局变量记录上次的路径//stringbuilder线程不安全-单线程中效率高
    StringBuilder sbParent = new StringBuilder();

    private Map getCodeTable(HefumanTreeNode hefumanTreeNode) {

        //所有元素都在叶子节点上
        putByteAndPath(hefumanTreeNode.getLeft(), "0", sbParent);
        putByteAndPath(hefumanTreeNode.getRight(), "1", sbParent);
        return mapTable;
    }

    private void putByteAndPath(HefumanTreeNode node, String s, StringBuilder sb) {
        //父节点记录
        StringBuilder sb2 = new StringBuilder(sb);

        sb2.append(s);
        //    如果节点为空-说明他还不是叶子节点-就继续向下走【同一方向】
        if (node.getB_value() == null) {
            putByteAndPath(node.getLeft(), "0", sb2);
            putByteAndPath(node.getRight(), "1", sb2);
        } else {

            mapTable.put(node.getB_value(), sb2);

        }


    }

    private HefumanTreeNode hefuman(Map<Byte,Integer> map) {
        //将每一个ASCII码 建成一个独立的树【为了后面与其他ASCII码关联】
        ArrayList<HefumanTreeNode> hefumanTreeNodes = new ArrayList<>();
        for(Map.Entry<Byte,Integer> e:map.entrySet()){
            HefumanTreeNode hefumanTreeNode = new HefumanTreeNode(e.getKey(), e.getValue());
            hefumanTreeNodes.add(hefumanTreeNode);
        }
        //按照次数进行排序
        while (hefumanTreeNodes.size() > 1) {
            Collections.sort(hefumanTreeNodes);
            HefumanTreeNode right = hefumanTreeNodes.get(hefumanTreeNodes.size() - 1);
            HefumanTreeNode left = hefumanTreeNodes.get(hefumanTreeNodes.size() - 2);
            HefumanTreeNode parent = new HefumanTreeNode(null, right.getValue() + left.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            hefumanTreeNodes.remove(right);
            hefumanTreeNodes.remove(left);
            hefumanTreeNodes.add(parent);
        }
        HefumanTreeNode hefumanTreeNode = hefumanTreeNodes.get(0);
        return hefumanTreeNode;
    }

    private Map myMount(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer integer = map.get(b);
            if (integer == null) {
                map.put(b, 1);
            } else {
                map.put(b, integer + 1);
            }
        }
        return map;
    }
    /**
     * ----------------------------------------------------------赫夫曼编码解码结束---------------------------------------------------------------
     */


    /**
     * BST 搜索二叉树
     */
    @Test
    public void BSTTest() {
        //创建
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BSTree bsTree = new BSTree();
        for (int i : arr) {
            BSTNode bstNode = new BSTNode(i);
            bsTree.add(bstNode);
        }
        bsTree.midShow();

        System.out.println("----查找------");
        BSTNode search = bsTree.search(12);
        System.out.println(search);
        System.out.println("-------删除叶子节点-------");
        bsTree.delYeZi(1);
        bsTree.midShow();
        System.out.println("-------删除只有一个子节点的节点-------");
        bsTree.delNodeHaveOne(3);
        bsTree.midShow();
        System.out.println("-----删除有两个子节点的节点---------");
        //    思路1：找到左树的最大节点[要删除的节点的前驱节点]删除它并-替换要删除的节点
        //    思路2：找到右树的最小节点[要删除的节点的后驱节点]删除它并-替换要删除的节点
        bsTree.delNodeHaveTwo(7);//跟节点
        //bsTree.delNodeHaveTwo(10);//非跟节点
        //bsTree.delNodeHaveTwo(12);//不满足要求
        bsTree.midShow();
    }

    /**
     * AVL树---单旋转
     */
    @Test
    public void AvlTest() {
        //右旋转测试
        //int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        //左旋转测试
        //int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
        //左旋转测试
        //int[] arr = {8,9,6,7,5,4};
        //双旋转
        int[] arr = {8, 9, 5, 4, 6, 7};
        AVLTree tree = new AVLTree();
        for (int i : arr) {
            AVLNode son = new AVLNode(i);
            tree.add(son);
        }
        //获取树高度
        int height = tree.getRoot().height();
        System.out.println("--->" + tree.getRoot().getValue() + " 高度:" + height);
        tree.midShow();
    }

}
