package service;

import pojo.User;
import pojo.shiro.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissions(User user);
}
