package design_parrent.pojo.p14_mingling;

/**
 * 命令发起者
 */
public class Invoke {

    private Commond commond;

    public Invoke(Commond commond) {
        this.commond = commond;
    }

    public void startTask() {
        commond.excute();
    }


}
