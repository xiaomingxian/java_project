package design_parrent.pojo.p5_adeptor;

public class Adepter2 implements Target {
    //可以持有多个被适配对象
    private Adeptee adeptee;

    public Adepter2(Adeptee adeptee) {
        this.adeptee = adeptee;
    }


    @Override
    public void doSomeT() {
        adeptee.doRequest();
    }
}
