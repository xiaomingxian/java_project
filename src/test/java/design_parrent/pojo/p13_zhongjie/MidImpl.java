package design_parrent.pojo.p13_zhongjie;


import lombok.Data;

@Data
public class MidImpl extends Mid {

    public MidImpl() {
        super();
    }

    @Override
    public void contact(String msg, College college) {
        if (college == A) {//A发信息-B接受
            System.out.println("-->" + college.getName() + "向 " + B.getName() + " 发出信息：" + msg);
        } else {
            System.out.println("-->" + college.getName() + "向 " + A.getName() + " 发出信息：" + msg);
        }
    }

    @Override
    public void pubContact(String msg, College college) {
        if (list == null || list.size() == 0) {
            System.out.println("-没有订阅者");
        } else {
            list.stream().forEach(obj -> {
                System.out.println("-->订阅者：" + obj.getName() + "接受到来自：" + college.getName() + "信息：" + msg);
            });
        }

    }
}
