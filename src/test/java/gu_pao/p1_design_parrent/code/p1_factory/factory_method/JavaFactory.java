package gu_pao.p1_design_parrent.code.p1_factory.factory_method;

import gu_pao.p1_design_parrent.code.p1_factory.pojo.ICourse;
import gu_pao.p1_design_parrent.code.p1_factory.pojo.JavaCourse;

public class JavaFactory implements ICourseFactory {
    @Override
    public ICourse createFactory() {

        return new JavaCourse();
    }
}
