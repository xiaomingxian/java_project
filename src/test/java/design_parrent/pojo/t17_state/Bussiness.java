package design_parrent.pojo.t17_state;

public class Bussiness implements State {
    @Override
    public void status() {
        System.out.println("---入住状态");
    }
}
