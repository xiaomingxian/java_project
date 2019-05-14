package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class T2_CAS {

    /**
     * CAS 比较并交换模拟
     * 工作内存从主物理内存中读取数据  检测是否是期望的值 如果是则交换 否则不交换
     */
    @Test
    public void t1() {

        AtomicInteger atomicInteger = new AtomicInteger(7);

        boolean b = atomicInteger.compareAndSet(7, 11);//参数：期望值，赋值
        System.out.println("是否修改成功：" + b + "  数值：" + atomicInteger.get());
        b = atomicInteger.compareAndSet(7, 11);
        System.out.println("是否修改成功：" + b + "  数值：" + atomicInteger.get());

    }
}
