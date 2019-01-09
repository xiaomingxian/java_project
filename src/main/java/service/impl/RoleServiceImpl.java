package service.impl;

import dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;
import pojo.shiro.Role;
import service.RoleService;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getRoles(User user) {
        return roleMapper.getRoles(user);
    }
}
