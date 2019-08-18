package interview.t0.mianshi.test;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class T22_SE {

    public static void main(String[] args) {
        System.out.println(2 >> 2);//  /
        System.out.println(2 << 2);//  *

        //     并发集合
        ConcurrentHashMap map = null;
        map.put(null, null);// 不允许null k/v   if (key == null || value == null) throw new NullPointerException();


        HashSet hashSet=null;


    }

}
