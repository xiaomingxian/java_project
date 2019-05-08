package design_parrent.pojo.t17_state;

/**
 * 上下文
 */
public class Context {

    private State state;

    public void cahngeStatus(State s) {
        System.out.println("--更改状态");
        state = s;
        state.status();
    }

}
