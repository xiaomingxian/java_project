package gu_pao.p1_design_parrent.code.p1_factory.simple_factory;

import gu_pao.p1_design_parrent.code.p1_factory.pojo.ICourse;
import gu_pao.p1_design_parrent.code.p1_factory.pojo.JavaCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 *
 * 简单工厂模式(农耕社会小作坊)：封装创建逻辑，对外提供简单的调用接口
 * 属于创建形模式
 * <p>
 * <p>
 * 适用场景：
 * 1 负责创建的对象较少
 * 2 客户端只需传入工厂类参数，不需要关心创建逻辑
 * <p>
 * 缺点：
 * 1 工厂类的职责相对过重，增加新的产品时需要修改工厂类的判断逻辑，违背开闭原则
 * 2 不易扩展过于复杂的产品结构
 */
public class SimpleTest {

    public static void main(String[] args) throws Exception {

        //1 简单的使用标示进行判断
        ICourse javaCourse = CourseFactory.createByLogo("java");
        javaCourse.showInfo();

        //2 通过全类名
        ICourse pythonClass = CourseFactory.createByClassName("gu_pao.p1_design_parrent.code.p1_factory.simple_factory.PythonCourse");
        pythonClass.showInfo();

        //3 Class类
        ICourse byClazz = CourseFactory.createByClazz(JavaCourse.class);
        byClazz.showInfo();


        System.out.println("*********************** java中的简单工厂demo ************************");
        Calendar instance = Calendar.getInstance();//隐藏实现细节(eg:时区等之类的逻辑判断)//有时区穿参等重载方法
        Logger logger = LoggerFactory.getLogger(SimpleTest.class);//通过Class类方式
        logger.info("--->>>>>>通过Class类方式");
        Logger logger1 = LoggerFactory.getLogger("gu_pao.p1_design_parrent.code.p1_factory.simple_factory.SimpleTest");
        logger1.info("--------->>>>name");
    }

}
