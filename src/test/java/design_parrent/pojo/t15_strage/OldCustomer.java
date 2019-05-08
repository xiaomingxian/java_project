package design_parrent.pojo.t15_strage;

public class OldCustomer implements Strage {
    @Override
    public void strage(double price) {
        System.out.println("老客户打 5 折：" + price * 0.5);

    }
}
