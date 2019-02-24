package data_structure_and_dalgorithm.xianxing_;

import org.junit.Test;

import java.util.Arrays;

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
     * 冒泡
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
     * 希尔排序
     * 在插入排序的基础上加上 步长
     */
    @Test
    public void xier() {

        int[] array = {2, 1, 6, 10, 8, 4, 7, 9};

        //遍历步长   length/2-->4 2 1 0
        for (int i = array.length / 2; i > 0; i /= 2) {

            //  遍历本组中[以步长为单位前后移动，包含的数据都为本组数据]的所有元素
            for (int j = i; j < array.length; j++) {
                //    进行比较--和前面的比较，和后面的比较
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

}
