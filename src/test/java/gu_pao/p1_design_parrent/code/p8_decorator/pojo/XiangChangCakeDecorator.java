package gu_pao.p1_design_parrent.code.p8_decorator.pojo;

public class XiangChangCakeDecorator extends BaseDecorator {

    public XiangChangCakeDecorator(BaseCake baseCake) {
        super(baseCake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "+1个香肠";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
