package design_parrent.pojo.t17_state;

public class FreeStatus implements State {
    @Override
    public void status() {
        System.out.println("---空闲状态");
    }
}
