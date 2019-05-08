package design_parrent.pojo.t15_strage;

public class NewCustomer implements Strage {
    @Override
    public void strage(double price) {

        System.out.println("新客户打 9.5 折：" + price * 0.95);
    }
}
