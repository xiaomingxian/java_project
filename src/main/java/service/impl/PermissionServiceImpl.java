package service.impl;

import dao.PermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;
import pojo.shiro.Permission;
import service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> getPermissions(User user) {
        return permissionMapper.getPermissions(user);
    }
}
