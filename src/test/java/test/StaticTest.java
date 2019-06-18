package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StaticTest {

    private     static String s="x";

    public static void main(String[] args) {
        AC ac = new AC();
        ac.putvalue();


        Map map = ac.getMap();
        map.put("2", "2");

        System.out.println(map);


    }

    @Test
    public void t1(){
        s="xxx";
        System.out.println(StaticTest.s);
    }


}

class AC {

    static Map map = new HashMap();


    public Map getMap() {

        return map;
    }

    public void putvalue() {
        map.put("1", "1");
    }


}