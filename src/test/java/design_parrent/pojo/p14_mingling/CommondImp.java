package design_parrent.pojo.p14_mingling;

public class CommondImp implements Commond {

    //持有任务真正执行者的引用
    private Reciver reciver;

    public CommondImp(Reciver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void excute() {
        //可以在前后做一些处理
        reciver.doThing();
    }
}
