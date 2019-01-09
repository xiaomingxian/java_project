package dao;

import org.apache.ibatis.annotations.Param;
import pojo.User;
import pojo.shiro.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> getRoles(@Param("user") User user);
}
