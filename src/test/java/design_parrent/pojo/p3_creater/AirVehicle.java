package design_parrent.pojo.p3_creater;


/**
 * ---最终是从组装者获取到的信息
 */
public class AirVehicle {
    public static void main(String[] args) {
        //测试
        //组装者
        AirShipZuZhuang airShipZuZhuang = new AirShipZuZhuang();
        //创建者
        AirShipCreate airShipCreate = new AirShipCreate();
        AirShip airShip = airShipZuZhuang.zuZhuangOk(airShipCreate);

        System.out.println("产品信息：" + airShip);
    }

}

/**
 * 组装接口
 */
interface ZuZhuang<T> {
    T zuZhuangOk(Create create);
}

//飞船组装实现--可以有其他实现
class AirShipZuZhuang implements ZuZhuang<AirShip> {

    @Override
    public AirShip zuZhuangOk(Create create) {
        System.out.println("----组装者，开始调用建造者");
        AirShipCreate create1 = (AirShipCreate) create;
        AirShip airShip = create1.createOK();
        return airShip;
    }
}


/**
 * 创建接口
 */
interface Create<T> {
    T createOK();
}

//飞船制造实现---可以有其他实现
class AirShipCreate implements Create<AirShip> {

    AirShip airShip;

    @Override
    public AirShip createOK() {
        AirShip airShip = new AirShip();
        airShip.setFaShe(new FaShe());
        airShip.setGuiDao(new GuiDao());
        airShip.setTaoYi(new TaoYi());
        return airShip;
    }
}