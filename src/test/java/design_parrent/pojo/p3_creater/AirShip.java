package design_parrent.pojo.p3_creater;


import lombok.Data;

@Data
public class AirShip {
    TaoYi taoYi;
    GuiDao guiDao;
    FaShe faShe;
}

/**
 * 逃逸仓
 */
class TaoYi {
    public TaoYi() {
        System.out.println("-----逃逸仓创建");
    }

}

/**
 * 轨道仓
 */
class GuiDao {
    public GuiDao() {
        System.out.println("------轨道仓创建");
    }
}

/**
 * 发射仓
 */
class FaShe {
    public FaShe() {
        System.out.println("------发射仓创建");
    }

}