package design_parrent.test;

import design_parrent.pojo.t17_state.Bussiness;
import design_parrent.pojo.t17_state.Context;
import design_parrent.pojo.t17_state.FreeStatus;
import org.junit.Test;

public class T17_State {
    @Test
    public void t1() {

        FreeStatus freeStatus = new FreeStatus();

        Bussiness bussiness = new Bussiness();

        Context context = new Context();

        context.cahngeStatus(freeStatus);
        context.cahngeStatus(bussiness);

    }
}
