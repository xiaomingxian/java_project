package mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PersonMapper extends Mapper<User> {

    @Select("select * from user")
    List<User> select1();


    //自定义获取aop注解测试
    @mybatis.aop.Select("select * from user")
    List<User> selectOfMy();

}
