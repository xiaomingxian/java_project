package gu_pao.p1_design_parrent.code.p5_delegate;

import gu_pao.p1_design_parrent.code.p5_delegate.p1_simple.Boss;
import gu_pao.p1_design_parrent.code.p5_delegate.p1_simple.Leader;

/**
 * 委派模式测试
 */
public class DelegateTest {


    public static void main(String[] args) {

        //1 简单测试(老板-经理-员工)
        simple();


        //2 spring中的应用[delegate结尾，包含了Dispatcher的一般都是委派]
    }

    private static void simple() {
        Boss boss = new Boss();
        Leader leader = new Leader();
        boss.execute("加密", leader);
    }

}
