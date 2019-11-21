package gu_pao.p1_design_parrent.code.p4_proxy.pojo;

public class Son implements Person {
    @Override
    public Person findLove() {
        System.out.println("--儿子要求：肤白貌美大长腿");
        return null;
    }
}
