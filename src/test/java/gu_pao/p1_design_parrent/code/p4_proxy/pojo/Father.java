package gu_pao.p1_design_parrent.code.p4_proxy.pojo;

public class Father implements Person {
    private Person person;

    public Father(Person person) {
        this.person = person;
    }

    @Override
    public Person findLove() {
        person.findLove();
        System.out.println("-->父亲帮儿子找对象");
        return null;
    }
}
