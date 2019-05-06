package data_structure_and_dalgorithm.xianxing_;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 常用的8种排序算法
 */
public class T2_Sort {
    /**
     * 5类：
     * 交换排序：冒泡，快速
     * 插入排序：直接插入，xier
     * 选择排序：简单选择，堆排序
     * 归并排序
     * 基数排序
     */

    /**
     * 冒泡   ok
     */
    @Test
    public void maopao() {
        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        int count = 0;
        //第一轮比出最大值--第二轮就不比最大值
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - 1 - j; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
                count++;
            }
        }

        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.print("\n" + count);

    }

    /**
     * 快速排序
     * 定义前后指针
     * 如果前面的数字小于标准数，前指针就++，否则就停下来
     * 如果后面的数字大于标准数，后指针就--，否则就停下来
     * 两个指针都停下来是，将两个数字调换
     * 当前后指针相当时，进行递归【小于基准数的一部分，大于基准数的一部分】
     */
    @Test
    public void kuaiPai() {
        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};
        int kuai = kuai(array, 0, array.length, 0);
        System.out.println(kuai);
        System.out.println(Arrays.toString(array));
    }

    /**
     * @param array
     * @param start 开始位置
     * @param end   结束位置
     *              当开始位置小于结束位置时进行比较
     */
    private int kuai(int[] array, int start, int end, int count) {
        if (start < end) {
            //    以第一个数为基准，小于的放左边，大于的放右边
            int base = array[start];
            //定义指针
            int before = start;
            int after = end - 1;
            while (before < after) {

                while (before < after && array[before] < base) {
                    before++;
                    count++;
                }
                while (before < after && array[after] > base) {
                    after--;
                    count++;
                }
                //    前面两个while跳出说明左边的数字比基数大，右边的数字比基数小
                if (before < after) {
                    int temp = array[before];
                    array[before] = array[after];
                    array[after] = temp;
                    count++;
                }
            }
            //    将分成两段的数字进行递归
            //    ---start++,after-- 当相等时跳出以上循环，此时的before或者after是中间位置【即小于基数的部分与大于基数部分的分界线】
            kuai(array, start, before, count);
            kuai(array, before + 1, end, count);
            count++;
        }
        return count;

    }

    /**
     * 插入排序
     * 后一个数和前一个数比较：
     * 如果后一个大于前一个，指针下移
     * 如果后一个小于前一个，与前一个调换，再继续和前前一个比较，直到后面的大与前面的才停止
     * 如果指针==最后一个就结束
     */
    @Test
    public void cahru() {
        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        int target;
        for (int i = 1; i < array.length; i++) {

            int j = i;
            //将参照基准记录下来
            target = array[i];
            //如果前一个数 大于后一个数 就将前一个数赋值给后一个数
            while (j > 0 && target < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            //结束循环，再将后一个数变成基准数
            array[j] = target;

        }
        //cha(array);
        System.out.println(Arrays.toString(array));
    }

    private void cha(int[] arr) {
        int i, j;
        int n = arr.length;
        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (i = 1; i < n; i++) {
            j = i;
            target = arr[i];
            //如果后一个数小于前一个数，将让后一个数赋值给前一个数，否则就退出循环，再将记录下的值赋给，arr[j]
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = target;
        }

    }

    /**
     * 希尔排序【是插入排序的一种】
     * 在插入排序的基础上加上 步长
     */
    @Test
    public void xier() {

        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        //遍历步长   length/2-->4 2 1 0
        for (int i = array.length / 2; i > 0; i /= 2) {

            //  遍历本组中[以步长为单位前后移动，包含的数据都为本组数据]的所有元素
            for (int j = i; j < array.length; j++) {
                //    进行比较--
                for (int k = j - i; k >= 0; k -= i) {
                    if (array[k] > array[k + i]) {
                        int temp = array[k];
                        array[k] = array[k + i];
                        array[k + i] = temp;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }

    /**
     * 加上步长的插入排序
     * 以步长所在数为基准向前【步长为-增量】进行比较
     */
    @Test
    public void  xier2(){

        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        //    遍历步长
        for (int b = array.length / 2; b > 0; b /= 2) {
            //    　遍历步长基准数--第一个步长基准数向后推
            for (int i = b; i < array.length; i++) {
                //    以步长为基准向前比较【插入排序】 j>=0[j是前一个数所在位置，可以为0索引]
                for (int j = i - b; j >= 0; j -= b) {
                    if (array[j] > array[j + b]) {
                        int temp = array[j];
                        array[j] = array[j + b];
                        array[j + b] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(array));

    }

    /**
     * 选择排序
     */
    @Test
    public void choice() {

        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        //第一轮循环外层，第二层循环内层
        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }


    /**
     * 归并排序测试
     * 完全二叉树特
     * Array.sort() 用的是TimeSort
     * <p>
     * 每次合并操作的平均时间复杂度为O(n)，而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。
     *
     *
     * 思想：
     */
    @Test
    public void gb() {

        int[] array = {1, 3, 5, 7, 9, 11, 13, 14, 15, 2, 4, 6, 8, 10, 10};
        //起点   中间点【其实是两个数组的分界线】   最后索引
        sort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));


    }

    /**
     * 使得集合变得有序
     *
     */
    private void sort(int[] arr, int start, int end) {
        //当只有一个元素时结束递归
        if (start < end) {
            //递归到最小单位进行排序
            int mid = (start + end) / 2;
            sort(arr, start, mid);
            sort(arr, mid + 1, end);
            //有序数组归并排序
            gui(arr, start, mid, end);
        }
    }

    /**
     * 两个有序的数组进行排序
     * xiao          zhong      da
     * 起点【前半段】   起点【后半段】
     * 创建一个临时数组
     * 同时遍历两段数组--判断谁小就把谁放在前面
     * 返回临时数组
     *
     * @param array
     */
    private  void gui(int[] array, int low, int mid, int hight) {
        //数组的创建可以提前以减少开销
        int[] temp = new int[array.length];
        int i = low;
        int j = mid + 1;
        int x = 0;
        while (i <= mid && j <= hight) {
            if (array[i] > array[j]) {
                temp[x] = array[j];
                j++;

            } else {
                temp[x] = array[i];
                i++;
            }
            x++;
        }
        //    将剩余的元素放在一边
        while (i <= mid && x < temp.length) {
            temp[x] = array[i];
            i++;
            x++;
        }
        while (j <= hight && x < temp.length) {
            temp[x] = array[j];
            j++;
            x++;
        }
        // 将排好序的数组元素放到原数组中
        x=0;
        while (low <= hight) {
            array[low++]=temp[x++];
        }

    }

    /**
     * 基数排序
     * ---＞【方法1】二维数组
     * ---＞【方法2】数组【队列】
     */
    @Test
    public void jishu() {

        int[] array = {92, 971, 6, 8010, 7768, 498, 7, 9};
        //int[] array = {2, 5, 6, 1, 7, 6, 7, 9};

        int length = 0;
        //找出最大长度的数字
        for (int i = 0; i < array.length; i++) {
            String yuan = array[i] + "";
            int l = yuan.length();
            if (l > length) {
                length = l;
            }
        }
        int[][] sort = new int[10][array.length];
        HashMap<Integer, Integer> index = new HashMap<>();
        index.put(0, 0);
        index.put(1, 0);
        index.put(2, 0);
        index.put(3, 0);
        index.put(4, 0);
        index.put(5, 0);
        index.put(6, 0);
        index.put(7, 0);
        index.put(8, 0);
        index.put(9, 0);
        for (int i = 0, chu = 10; i < length; i++, chu *= 10) {
            //根据i求出相应位上的数字
            for (int j = 0; j < array.length; j++) {

                int chushu = 1;
                //对应位上的数字是几--计算方式 xxx/10^i%10
                for (int xx = 0; xx < i; xx++) {
                    chushu *= 10;
                }
                int weizhi = array[j] / chushu % 10;

                Integer i2 = index.get(weizhi);
                sort[weizhi][i2] = array[j];
                i2++;
                index.put(weizhi, i2);
            }
            //    一轮完后将数字拿出来放入原数组
            int yuan_i = 0;
            for (int j = 0; j < 10; j++) {
                for (int in = 0; in < index.get(j); in++) {
                    array[yuan_i] = sort[j][in];
                    yuan_i++;
                }
            }
            System.out.println("----" + Arrays.toString(array));
            //    一轮排序完成后，将记录指针归位
            Set<Integer> keys = index.keySet();
            for (Integer k : keys) {
                index.put(k, 0);
            }
        }
        System.out.println(Arrays.toString(array));

    }

    public static void main(String[] args) {
        System.out.println(6 % 100);
    }


}
