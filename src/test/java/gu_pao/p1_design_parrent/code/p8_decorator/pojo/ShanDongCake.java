package gu_pao.p1_design_parrent.code.p8_decorator.pojo;

public class ShanDongCake implements BaseCake {
    @Override
    public String getMsg() {
        return "普通山东煎饼";
    }

    @Override
    public int getPrice() {
        return 5;
    }
}
