package gu_pao.p1_design_parrent;

import org.junit.Test;

import javax.xml.soap.SAAJResult;
import java.util.Arrays;
import java.util.HashMap;

public class T {

    @Test
    public void ttttt() {

        HashMap<String, String> map = new HashMap<>();

        String s = "${a=='x'}";
        String ss = s.replace("$", "").
                replace("{", "").
                replace("}", "").
                replace("'", "");
        System.out.println(ss);

        if (ss.contains("(") && ss.contains(")")) {
            ss = ss.replace("(", "").replace(")", "");
        }

        if (ss.contains("||") && ss.contains("&&")) {
            ss = ss.replace("||", "-");
            ss = ss.replace("&&", "-");
        }
        if (ss.contains("||") && !ss.contains("&&")) {
            ss = ss.replace("||", "-");
        }
        if (!ss.contains("||") && ss.contains("&&")) {
            ss = ss.replace("&&", "-");
        }
        System.out.println(ss);
        if (ss.contains("-")) {
            String[] split = ss.split("-");
            Arrays.stream(split).forEach(i -> {
                map.put(i.split("==")[0], i.split("==")[1]);
            });
        } else {
            //只有一个条件
            map.put(ss.split("==")[0], ss.split("==")[1]);
        }

        System.out.println(map);

    }
}
