package design_parrent.pojo.p11_zerenlian;


import lombok.Data;

@Data
public class Person extends WorkFlow {

    public Person(String name, Integer days) {
        super(name, days);
    }

    @Override
    public void doTask(Leave leave) {
        if (leave.getDays() < this.days) {
            System.out.println("----当前审批人：" + this.name + " 进行办理");
        } else {
            if (this.next != null) {
                System.out.println("----当前审批人:" + this.name + " 不具备审批资格，交给下一审批人：" + this.next.getName() + " 处理");
                this.next.doTask(leave);
            } else {
                System.out.println("----下一审批人未指定");
            }
        }
    }
}
