package gu_pao.p1_design_parrent.code.p5_delegate.p1_simple;

import java.util.HashMap;
import java.util.Map;

public class Leader {


    static Map<String, Imployee> map = new HashMap<>();

    static {
        map.put("架构", new ImployeeA());
        map.put("加密", new ImployeeB());
    }

    public void doTask(String command) {
        map.get(command).doTaskSelf(command);
    }
}
