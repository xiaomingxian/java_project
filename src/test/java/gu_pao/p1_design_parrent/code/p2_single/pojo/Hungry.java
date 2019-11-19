package gu_pao.p1_design_parrent.code.p2_single.pojo;

public class Hungry {

    /**
     * final防止被修改(反射等方式)
     */


    //method1
    //private static  final Hungry hungry=new Hungry();


    //method2
    private static final Hungry hungry;

    static {
        hungry = new Hungry();
    }

    private Hungry() {
    }

    public static Hungry getInstance() {
        return hungry;
    }


}
