package gu_pao.p1_design_parrent.code.p6_strategy.strategy;

import java.util.HashMap;
import java.util.Map;

public class ShopingTypeFactory {


    private static Map<String, Shoping> SHOPING_TYPE = new HashMap<>();

    static {
        SHOPING_TYPE.put("five", new Shoping5());
        SHOPING_TYPE.put("eight", new Shoping8());
    }

    static Shoping SHOP_NORMAL = new ShopingNormal();

    private ShopingTypeFactory() {
    }

    public static Shoping getShopingType(String type) {
        Shoping shoping = SHOPING_TYPE.get(type);
        return shoping == null ? SHOP_NORMAL : shoping;
    }


}
