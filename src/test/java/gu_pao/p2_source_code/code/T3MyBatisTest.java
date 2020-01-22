package gu_pao.p2_source_code.code;

import tk.mybatis.mapper.session.Configuration;

public class T3MyBatisTest {
    public static void main(String[] args) {

        //编程式

        //1 核心配置文件
        configuration();

        //2



    }

    private static void configuration() {
        Configuration configuration = new Configuration(); //<configuration>...
        //configuration.set...

        //java类型与数据库类型如何匹配  TypeHandlerRegister中预制了 对应关系

        //Blog(数据库中的大文本)转对象?

        //mysql5.7有json类型
        //如何扩展自定义类型 ....#{name,jdbcType=VARCHAR,typeHandler=自定义handler}
    }
}
