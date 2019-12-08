package gu_pao.p2_source_code.code;


import springx.context.ApplicationX;

public class T2SpringTest {

    public static void main(String[] args) {
        //1 ioc
        ioc();

    }

    private static void ioc() {

        ApplicationX applicationX = new ApplicationX("classpath:springx/application.properties");
        Object bean = applicationX.getBean("applicationX");
        Object t3 = applicationX.getBean("t3");
        Object bean2 = applicationX.getBean(ApplicationX.class);
        System.out.println(applicationX + "\nName获取到的Bean信息：" + bean);
        System.out.println(applicationX + "\nType获取到的Bean信息：" + bean2);
        System.out.println(applicationX + "\n自动注入注入的类：" + t3);

    }

}
