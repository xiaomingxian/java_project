package mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import pojo.User;

import java.util.List;

public interface PersonMapper {

    @Select("select * from user")
    List<User> select();

    @mybatis.aop.Select("select * from user")
    List<User> selectOfMy();

}
