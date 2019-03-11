package data_structure_and_dalgorithm.pojo;

public class BinaryArray {
    int[] array;

    public BinaryArray(int[] array) {
        this.array = array;
    }

    //重载-从跟节点进行遍历
    public void showAll() {
        showAll(0);
    }

    //    按照顺序二叉树的方式进行遍历
    public void showAll(int index) {
        if (array == null || array.length == 0) {
            return;
        }
        //先看跟节点
        System.out.print(array[index] + ",");
        //    在看左子节点
        int left = index * 2 + 1;
        if (left <array.length ) {
            showAll(left);
        }
        //    再看右子节点
        int right = index * 2 + 2;
        if (right < array.length ) {
            showAll(right);
        }
    }


}
