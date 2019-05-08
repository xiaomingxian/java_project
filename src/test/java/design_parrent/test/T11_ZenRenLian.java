package design_parrent.test;

import design_parrent.pojo.p11_zerenlian.Leave;
import design_parrent.pojo.p11_zerenlian.Person;
import org.junit.Test;

public class T11_ZenRenLian {

    @Test
    public void t1() {
        Leave leave = new Leave("请假", 6, "回家");

        Person tom = new Person("tom", 4);
        Person jerry = new Person("jerry", 10);

        tom.setNext(jerry);

        tom.doTask(leave);

    }
}
