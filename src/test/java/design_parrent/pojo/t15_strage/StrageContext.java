package design_parrent.pojo.t15_strage;

/**
 * 策略管理上下文
 */
public class StrageContext {


    private Strage strage;

    public StrageContext(Strage strage) {
        this.strage = strage;
    }

    public void showStrage(double price) {
        strage.strage(price);
    }


}

