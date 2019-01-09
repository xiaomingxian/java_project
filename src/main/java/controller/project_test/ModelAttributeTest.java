package controller.project_test;


import org.apache.ibatis.annotations.ResultMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.project_test.Son;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@RestController
public class ModelAttributeTest {


    @RequestMapping("test")
    public void test(@ModelAttribute Son son) {

        System.out.println(son);

        System.out.println(son.getAge() + "  " + son.getName());


    }

    public static void main(String[] args) throws ParseException {
        System.out.println("s" instanceof String);
        System.out.println(new Date(0).toString());

        String s = "-1000 00 00 00 00 00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
        Date parse = simpleDateFormat.parse(s);
        System.out.println(simpleDateFormat.format(parse));


        System.out.println(parse.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t = System.currentTimeMillis();
        t = t / (1000 * 3600 * 24) * (1000 * 3600 * 24);
        System.out.println(sdf.format(new Timestamp(t)));
        t = System.currentTimeMillis();
        t = t / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        String format = sdf.format(new Timestamp(t));
        System.out.println(format);


        // String ss = "jdbc:mysql://10.149.51.80:3306/test?relaxAutoCommit=true&zeroDateTimeBehavior=convertToNull";


    }






}
