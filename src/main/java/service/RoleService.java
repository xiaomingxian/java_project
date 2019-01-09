package service;

import pojo.User;
import pojo.shiro.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles(User user);
}
