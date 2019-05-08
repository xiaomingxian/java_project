package design_parrent.pojo.p11_zerenlian;

import lombok.Data;

@Data
public abstract class WorkFlow {

    protected String name;

    protected Integer days;

    protected WorkFlow next;

    public WorkFlow(String name, Integer days) {
        this.name = name;
        this.days = days;
    }

    public abstract void doTask(Leave leave);


}
