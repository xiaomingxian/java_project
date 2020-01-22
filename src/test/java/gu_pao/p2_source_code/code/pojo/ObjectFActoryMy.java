package gu_pao.p2_source_code.code.pojo;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

public class ObjectFActoryMy extends DefaultObjectFactory {

    @Override
    public <T> T create(Class<T> type) {

        System.out.println("创建对应的方法,对象类型:[" + type + "]");
        if (type.equals("xxx")){//可以对对象做干预 改变属性值什么的
            //return
        }


        return super.create(type);
    }
}
