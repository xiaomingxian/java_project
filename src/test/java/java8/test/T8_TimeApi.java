package java8.test;

import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T8_TimeApi {

    @Test
    public void chuanT() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("2019-2-2");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        ArrayList<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> submit = pool.submit(task);
            list.add(submit);
        }
        for (Future<Date> future : list) {
            System.out.println(future);
        }
        //list.stream().forEach(System.out::println);
    }

    @Test
    public void java8Api(){
        //不包含时分秒
        LocalDate now = LocalDate.now();
        System.out.println(now+" 获取年月日 "+now.getYear()+" "+now.getMonth()+" "+now.getDayOfMonth());

    //
        LocalDate of = LocalDate.of(2020, 4, 27);//月份不必从0开始
        System.out.println(of);

        System.out.println("检查连个时间是否相当："+now.equals(of));

        //判断生日是否相等 月-日
        MonthDay of1 = MonthDay.of(of.getMonth(), of.getDayOfMonth());
        MonthDay md = MonthDay.of(now.getMonth(), now.getDayOfMonth());
        System.out.println(of1.equals(md));


    //    格式化
        LocalDateTime now1 = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(now1);
        System.out.println(strDate2);

        LocalDateTime parse = LocalDateTime.parse(strDate2, dtf2);
        System.out.println(parse);

    }
}
