package gu_pao.p2_source_code.code.mybatis_test;

import dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.User;

import java.io.InputStream;

public class T1_FirstCache {

    @Test
    public void firstCache() throws Exception {
        //工作原理 上 21.58
        //核心配置文件
        String resource = "properties/mybatis_hexin.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();

        try {
            UserMapper mapper1 = session1.getMapper(UserMapper.class);
            UserMapper mapper2 = session1.getMapper(UserMapper.class);
            User user = mapper1.selectByPrimaryKey("1");
            System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
