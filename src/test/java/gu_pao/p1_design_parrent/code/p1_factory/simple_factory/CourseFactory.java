package gu_pao.p1_design_parrent.code.p1_factory.simple_factory;

import gu_pao.p1_design_parrent.code.p1_factory.pojo.ICourse;
import gu_pao.p1_design_parrent.code.p1_factory.pojo.JavaCourse;
import gu_pao.p1_design_parrent.code.p1_factory.pojo.PythonCourse;

public class CourseFactory {

    /**
     * 简单的通过标示进行判断
     *
     * @param name
     * @return
     */
    public static ICourse createByLogo(String name) {
        ICourse iCourse = null;
        if ("java".equals(name)) iCourse = new JavaCourse();
        if ("python".equals(name)) iCourse = new PythonCourse();
        return iCourse;
    }


    /**
     * 通过全类名 使用反射进行判断
     */
    public static ICourse createByClassName(String className) throws Exception {
        return (ICourse) Class.forName(className).newInstance();
    }


    /**
     * 通过类型创建
     */
    public static ICourse createByClazz(Class clazz) throws Exception {
        return (ICourse) clazz.newInstance();
    }
}
