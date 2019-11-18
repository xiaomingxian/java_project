package gu_pao.p1_design_parrent.code.p1_factory.factory_method;

import gu_pao.p1_design_parrent.code.p1_factory.pojo.ICourse;

public class FactoryMethodTest {

    /**
     * 1 制定一个顶层的工厂接口(工厂规范)
     * <p>
     * 2 不同的工厂负责自己相关的功能(单一职责)
     * <p>
     * 特点
     * 创建对象需要大量的重复代码(每种类型都有自己的工厂)
     * 客户端不依赖于产品实例如何被创建(只需关心产品对应的工厂)，无需实现细节(工厂通用)
     * 一个类通过其子类/实现类来指定创建哪个对象(工厂规则的实现)
     * 加入新产品符合开闭原则，提高了系统的可扩展性
     * <p>
     * <p>
     * <p>
     * <p>
     * 缺点：
     * 1 类膨胀(具体的工厂类)，增加代码结构复杂度
     * 2 增加了系统的抽象度和理解难度
     */
    public static void main(String[] args) {

        ICourseFactory javaFactory = new JavaFactory();
        ICourse javaCourse = javaFactory.createFactory();
        javaCourse.showInfo();

    }
}
