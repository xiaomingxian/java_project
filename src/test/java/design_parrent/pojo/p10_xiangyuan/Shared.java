package design_parrent.pojo.p10_xiangyuan;

public class Shared extends Flyweight {

    public Shared(String var, Boolean isN) {
        super(var, isN);
    }

    @Override
    public void operate() {
        System.out.println("--------->共享的信息：");
    }
}
