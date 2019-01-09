package dao;

import org.apache.ibatis.annotations.Param;
import org.quartz.PersistJobDataAfterExecution;
import pojo.User;
import pojo.shiro.Permission;

import java.util.List;

public interface PermissionMapper {
    List<Permission> getPermissions(@Param("user") User user);
}
