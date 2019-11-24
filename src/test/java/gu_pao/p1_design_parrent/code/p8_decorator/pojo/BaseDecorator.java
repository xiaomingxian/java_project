package gu_pao.p1_design_parrent.code.p8_decorator.pojo;

public class BaseDecorator implements BaseCake {

    private BaseCake baseCake;

    public BaseDecorator(BaseCake baseCake) {
        this.baseCake = baseCake;
    }


    @Override
    public String getMsg() {
        return baseCake.getMsg();
    }

    @Override
    public int getPrice() {
        return baseCake.getPrice();
    }
}
