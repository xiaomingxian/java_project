package dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.User;
import pojo.UserExample;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("<script>" +
            "select * from user where id in" +
            "<foreach collection='ids' open='(' item='id_' separator=',' close=')'> #{id_}</foreach>" +
            "</script>")
    List<User> forSearch(@Param("ids") ArrayList<Integer> ids);
}