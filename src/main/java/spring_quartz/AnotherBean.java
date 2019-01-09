package spring_quartz;


import org.springframework.stereotype.Component;

@Component("anotherBean")
public class AnotherBean {

    public void printMsg() {

        System.out.println("存储位置...");
    }

}