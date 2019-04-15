package jvm.test;

public class T4_GCTest {

    T4_GCTest t = null;

    public T4_GCTest() {
        //    没创建一个对象就会分配这么一块空间 20m
        byte[] b = new byte[20 * 1024 * 1024];//
    }

    /**
     * 引用技术算法测试
     * 实际Java并不使用此算法
     *
     * @param args
     */
    public static void main(String[] args) {
        //-XX:+PrintGCDetails
        T4_GCTest t1 = new T4_GCTest();
        T4_GCTest t2 = new T4_GCTest();

        //堆中互指
        t1.t = t2;
        t2.t = t1;

        t1 = null;
        t2 = null;

        System.gc();

//        parallels回收器

//[PSYoungGen: 47551K->1138K(76288K)] --->内存变化信息
//[GC (System.gc()) [PSYoungGen: 47551K->1138K(76288K)] 47551K->1146K(251392K), 0.0013557 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
//[Full GC (System.gc()) [PSYoungGen: 1138K->0K(76288K)] [ParOldGen: 8K->1045K(175104K)] 1146K->1045K(251392K), [Metaspace: 3156K->3156K(1056768K)], 0.0045874 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
//        Heap
//        PSYoungGen      total 76288K, used 655K [0x000000076ab00000, 0x0000000770000000, 0x00000007c0000000)
//        eden space 65536K, 1% used [0x000000076ab00000,0x000000076aba3ee8,0x000000076eb00000)
//        from space 10752K, 0% used [0x000000076eb00000,0x000000076eb00000,0x000000076f580000)
//        to   space 10752K, 0% used [0x000000076f580000,0x000000076f580000,0x0000000770000000)
//        ParOldGen       total 175104K, used 1045K [0x00000006c0000000, 0x00000006cab00000, 0x000000076ab00000)
//        object space 175104K, 0% used [0x00000006c0000000,0x00000006c01057f8,0x00000006cab00000)
//        Metaspace       used 3165K, capacity 4496K, committed 4864K, reserved 1056768K
//        class space    used 344K, capacity 388K, committed 512K, reserved 1048576K


    }
}
