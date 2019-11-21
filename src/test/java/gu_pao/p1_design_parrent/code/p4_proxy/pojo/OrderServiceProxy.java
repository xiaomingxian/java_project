package gu_pao.p1_design_parrent.code.p4_proxy.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceProxy implements IOrderService {


    private IOrderService iOrderService;


    public OrderServiceProxy(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @Override
    public void createOrder(Order order) {
        Date createTime = order.getCreateTime();
        String year = new SimpleDateFormat("yyyy").format(createTime);
        DynammicDataSource.set(year);
        System.out.println("设置数据源：[DB_" + year + "]");
        System.out.println("进行具体db操作");
        iOrderService.createOrder(order);
        System.out.println("重置默认数据源");
        DynammicDataSource.resetDataSource();
        System.out.println("重置后的数据源：[DB_" + DynammicDataSource.get() + "]");
    }
}
