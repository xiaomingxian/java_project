package gu_pao.p1_design_parrent.code.p2_single.pojo;

public class FanShe {


    private static final FanShe fanShe = new FanShe();

    private FanShe() {
        if (fanShe != null) {
            throw new RuntimeException("不允许反射创建实例");
        }
    }

    public static FanShe getInstance() {
        return fanShe;
    }


}
