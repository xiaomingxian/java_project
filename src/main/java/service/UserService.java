package service;


import pojo.User;
import pojo.UserExample;
import pojo.shiro.Permission;
import pojo.shiro.Role;

import java.util.List;


public interface UserService {

    void register(User lUser);


    List<User> getAll(UserExample userExample);

    void update(User user);

}
