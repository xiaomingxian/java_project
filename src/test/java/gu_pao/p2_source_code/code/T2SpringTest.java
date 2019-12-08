package gu_pao.p2_source_code.code;


import springx.context.ApplicationX;

public class T2SpringTest {

    public static void main(String[] args) {

        ioc();

    }

    private static void ioc() {

        ApplicationX applicationX = new ApplicationX("classpath:springx/application.properties");
        Object bean = applicationX.getBean("applicationX");
        System.out.println(applicationX + "\n获取到的Bean信息：" + bean);

    }

}
