package design_parrent.pojo.t16_template;

public abstract class BankTemplate {

    public void befoe() {
        System.out.println("--排队");
    }

    public abstract void doTask();


    public void after() {
        System.out.println("--评价");
    }

    public final void process() {
        this.befoe();
        this.doTask();//抽象方法延迟到子类进行调用
        this.after();
    }


}
