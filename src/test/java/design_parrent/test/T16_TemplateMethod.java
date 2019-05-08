package design_parrent.test;

import design_parrent.pojo.t16_template.BankTemplate;
import org.junit.Test;

public class T16_TemplateMethod {
    @Test
    public void t1() {


        BankTemplate bankTemplate = new BankTemplate() {
            @Override
            public void doTask() {
                System.out.println("--存钱业务");
            }
        };
        bankTemplate.process();

        BankTemplate bankTemplate1 = new BankTemplate() {
            @Override
            public void doTask() {
                System.out.println("--取钱业务");
            }
        };
        bankTemplate1.process();


    }

}
