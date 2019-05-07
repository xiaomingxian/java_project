package design_parrent.pojo.p6_proxy;

public class StaticProxed implements Proxy_ {
    @Override
    public void doSomeTh() {
        System.out.println("---被代理 类 方法执行");
    }
}
