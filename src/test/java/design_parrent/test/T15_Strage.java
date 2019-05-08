package design_parrent.test;

import design_parrent.pojo.t15_strage.NewCustomer;
import design_parrent.pojo.t15_strage.OldCustomer;
import design_parrent.pojo.t15_strage.StrageContext;
import org.junit.Test;

public class T15_Strage {

    @Test
    public void t1() {

        //老客户
        OldCustomer oldCustomer = new OldCustomer();
        //新客户
        NewCustomer newCustomer = new NewCustomer();


        StrageContext old = new StrageContext(oldCustomer);
        StrageContext newC = new StrageContext(newCustomer);

        old.showStrage(100);
        newC.showStrage(100);

    }


}
