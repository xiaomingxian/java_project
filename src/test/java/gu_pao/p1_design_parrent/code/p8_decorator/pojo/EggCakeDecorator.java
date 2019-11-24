package gu_pao.p1_design_parrent.code.p8_decorator.pojo;

public class EggCakeDecorator extends BaseDecorator {

    public EggCakeDecorator(BaseCake baseCake) {
        super(baseCake);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }
}
