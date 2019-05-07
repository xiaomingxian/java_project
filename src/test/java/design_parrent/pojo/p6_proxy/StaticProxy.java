package design_parrent.pojo.p6_proxy;

public class StaticProxy implements Proxy_ {

    //被代理类的引用
    private StaticProxed proxed;

    public StaticProxy() {
    }

    public StaticProxy(StaticProxed proxed) {
        this.proxed = proxed;
    }

    @Override
    public void doSomeTh() {
        this.doBefore();

        proxed.doSomeTh();

        this.doAfter();
    }

    private void doBefore() {
        System.out.println("---------代理类 在 执行方法之前.....");
    }

    private void doAfter() {
        System.out.println("---------代理类 在 执行方法之前.....");
    }


}
