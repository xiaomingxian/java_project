package design_parrent.pojo.p10_xiangyuan;

public class UnShared extends Flyweight {

    public UnShared(String wb, Boolean isN) {
        super(wb, isN);
    }

    @Override
    public void operate() {
        System.out.println("--------->不共享的信息：" + wb + "-" + getNb());

    }
}
