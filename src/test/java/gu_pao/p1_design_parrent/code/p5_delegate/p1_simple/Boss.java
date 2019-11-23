package gu_pao.p1_design_parrent.code.p5_delegate.p1_simple;

public class Boss {


    public void execute(String command,Leader leader) {
        leader.doTask(command);
    }
}
