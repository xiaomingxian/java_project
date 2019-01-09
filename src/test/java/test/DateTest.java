package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) throws ParseException {
        // pojoDataSort();


        // calendarTest();


        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String s = "2018-1-1 10:10:10";
        // String ss = "2018-1-1 11:20:10";
        // Date parse1 = simpleDateFormat.parse(s);
        // Date parse2 = simpleDateFormat.parse(ss);
        // System.out.println(parse1.compareTo(parse2));


        long xx = 365 * 24 * 3600 * 1000;

        long x = 20991231235959L / 1000 / 3600 / 24;
        System.out.println(x);
        System.out.println(xx);

    }

    private static void calendarTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int y = calendar.get(Calendar.YEAR);
        System.out.println(y);

        int m = calendar.get(Calendar.MONTH) + 1;
        System.out.println(m);

        int d = calendar.get(Calendar.DATE);
        System.out.println(d);
    }

}
