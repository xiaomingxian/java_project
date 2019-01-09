package test;

import org.apache.commons.lang.StringUtils;

public class Lang3 {

    public static void main(String[] args) {
        String x = null;
        String xx = "";
        System.out.println("null值判断：" + StringUtils.isBlank(x) +
                "   空串判断：" + StringUtils.isBlank(xx));
    }
}
