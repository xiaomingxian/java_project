package gu_pao.p1_design_parrent.code.p6_strategy;

import gu_pao.p1_design_parrent.code.p6_strategy.strategy.*;

/**
 * 策略模式
 */
public class StrategyTest {

    public static void main(String[] args) {

        //1 简单测试
        //simple();


        //2 工厂+策略
        //factoryAndStrategy();


        //3 spring中的Dispatcher仿写  mvc包下



    }

    private static void factoryAndStrategy() {

        String type="five";
        GoShoping goShoping = new GoShoping(ShopingTypeFactory.getShopingType(type));
        goShoping.goShoping();

    }

    private static void simple() {
        String day = "1111";
        Shoping shoping = null;
        if (day.equals("618")) {
            shoping = new Shoping5();
        } else if (day.equals("1111")) {
            shoping = new Shoping8();
        }//.....

        GoShoping goShoping = new GoShoping(shoping);
        goShoping.goShoping();
    }


}
