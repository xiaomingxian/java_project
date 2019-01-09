package test;

import java.util.HashMap;
import java.util.Map;

public class StaticTest {

    public static void main(String[] args) {
        AC ac = new AC();
        ac.putvalue();


        Map map = ac.getMap();
        map.put("2", "2");

        System.out.println(map);


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