package gu_pao.p1_design_parrent.code.p6_strategy.mvc;

public class MemberController implements Controller {

    public String memberQuery() {
        System.out.println("member query ...");
        return "调用了 memberQuery 方法";
    }
}
