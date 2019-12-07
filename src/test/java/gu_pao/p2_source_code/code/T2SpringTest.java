package gu_pao.p2_source_code.code;


import springx.context.ApplicationX;

public class T2SpringTest {

    public static void main(String[] args) {

        ApplicationX applicationX = new ApplicationX("classpath:springx/application.properties");
        System.out.println(applicationX);


    }

}
