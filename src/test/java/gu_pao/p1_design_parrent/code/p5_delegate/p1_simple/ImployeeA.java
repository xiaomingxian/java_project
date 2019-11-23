package gu_pao.p1_design_parrent.code.p5_delegate.p1_simple;

public class ImployeeA implements Imployee {
    @Override
    public void doTaskSelf(String command) {
        System.out.println("我是员工A,我擅长架构:收到的命令->" + command);
    }
}
