package data_structure_and_dalgorithm;

import org.junit.Test;

public class T1_Time_O_n {

    @Test
    public void t1() {
        int n = 100;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.println("---->");
            }
        }
        //    这条的时间复杂度
        //    i=0时循环n次 i=1时循环n-1次
        //    n+(n-1)+(n-2)...2+1==>(n+1)(n/2)==>(n^2+n)(1/2)===>直接看最高次幂，忽略常数==>O(n^2)
    }

    @Test
    public void t2() {
        int i = 1;
        int n = 100;
        while (i < n) {
            i = i * 2;
        }
        //i=1时i=2 4 ,8,16 ;i每增加一次循环次数相对n就减少2倍 log(2)n
        //    时间复杂度为：O(logn)
        //    假设循环x次   每次i*2后就接近n ==>2^x=n==>x=log(2)n
    }


}
