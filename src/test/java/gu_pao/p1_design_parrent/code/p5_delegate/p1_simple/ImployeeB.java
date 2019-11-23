package gu_pao.p1_design_parrent.code.p5_delegate.p1_simple;

public class ImployeeB implements Imployee {
    @Override
    public void doTaskSelf(String command) {
        System.out.println("我是员工B,我擅长加密：收到的命令->" + command);
    }
}
