package jvm.test;

import jvm.pojo.JconsolePojo;

import java.util.ArrayList;

public class T1_JconsoleTest {
    //  增大对象所占内存到128k  public防止垃圾回收器回收？why   理论上堆内存一直上升
    public byte[] b = new byte[128 * 1024];

    //public T1_JconsoleTest(){
    //     //作为局部变量可被回收
    //     byte[] b = new byte[128 * 1024];
    //
    //}

    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        full(1000);

    }

    private static void full(int size) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new JconsolePojo());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
