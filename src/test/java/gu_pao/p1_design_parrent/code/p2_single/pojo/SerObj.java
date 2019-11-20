package gu_pao.p1_design_parrent.code.p2_single.pojo;

import java.io.Serializable;

public class SerObj implements Serializable {

    private static final SerObj serObj = new SerObj();

    private SerObj() {
    }

    public static SerObj getInstance() {
        return serObj;
    }


    private Object readResolve() {
        return serObj;
    }
}
