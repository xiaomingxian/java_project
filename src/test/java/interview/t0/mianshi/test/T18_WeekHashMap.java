package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.WeakHashMap;

public class T18_WeekHashMap {

    /**
     * 键对象的弱引用
     * WeakHashMap，此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值，
     */
    @Test
    public void t1() {
        WeakHashMap<Object, Object> map = new WeakHashMap<>();
        Integer i=new Integer(1);
        String value="aaa";
        map.put(i,value);

        i=null;
        System.out.println(map);

        System.gc();

        System.out.println(map);


    }
}
