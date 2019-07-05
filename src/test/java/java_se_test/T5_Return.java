package java_se_test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class T5_Return {

    Logger logger = LoggerFactory.getLogger(T5_Return.class);


    @Test
    public  void finallyTest(){
       String s= finallyReturn();
        System.out.println(s);


    }

    private String finallyReturn() {
        new SimpleDateFormat("HH:mm:ss");

        String s="a";

        try {
            int i = 1 / 0;
            return s;
        }catch (Exception e){
            s = "e";
            return s;
        }finally {
            s="b";
            logger.info("");
            return s;

        }
    }
}
