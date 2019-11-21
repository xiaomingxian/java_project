package gu_pao.p1_design_parrent.code.p4_proxy.pojo;

/**
 * 被代理类
 */
public class OrderServiceImpl implements IOrderService {
    @Override
    public void createOrder(Order order) {
        System.out.println("创建了订单：" + order.getName());
    }
}
