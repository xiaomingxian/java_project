package design_parrent.pojo.p5_adeptor;

/**
 * 适配器类
 */
public class Adepter extends Adeptee implements Target {

    /**
     * 实现了目标类---为了
     */
    @Override
    public void doSomeT() {
        super.doRequest();
    }
}
