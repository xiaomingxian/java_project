package system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;
import pojo.UserExample;
import pojo.shiro.Permission;
import pojo.shiro.Role;
import service.PermissionService;
import service.RoleService;
import service.UserService;

import java.util.List;

public class MyRealm extends AuthorizingRealm {




    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("---------授权-----------");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserExample example = new UserExample();
        //封装查询条件
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(principalCollection.toString());
        User user = userService.getAll(example).get(0);


        //查询角色和权限表进行授权
        List<Role> roles = roleService.getRoles(user);
        List<Permission> permissions = permissionService.getPermissions(user);
        System.out.println("permission : " + permissions);
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getKeyWords());
        }
        for (Permission permission : permissions) {
            simpleAuthorizationInfo.addStringPermission(permission.getKeyWords());
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        try {
            System.out.println("---------认证-----------");
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            // 查询用户
            UserExample example = new UserExample();
            //封装查询条件
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUserNameEqualTo(token.getUsername());
            User user = userService.getAll(example).get(0);

            System.out.println("-----"+user.getUserName()+"  "+user.getPassword());
            if (null != user) {
                return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), this.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
