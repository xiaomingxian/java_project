package gu_pao.p1_design_parrent.code.p6_strategy.strategy;

public class GoShoping {

    private Shoping shoping;

    public GoShoping(Shoping shoping) {
        this.shoping = shoping;
    }

    public void goShoping() {
        shoping.buy();
    }


}
