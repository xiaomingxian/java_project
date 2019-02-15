package activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Activiti自带用户组
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T12_UserGroup {

    @Autowired
    private ProcessEngine processEngine;


    /**
     * 创建用户和角色
     */
    @Test
    public void createUserGroup() {
        IdentityService identityService = processEngine.getIdentityService();
        //identityService.deleteUser("1");
        //identityService.deleteUser("2");
        //identityService.deleteUser("3");
        //identityService.deleteGroup("1");
        //identityService.deleteGroup("2");
        //identityService.deleteGroup("3");
        //    创建角色
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId("1");
        groupEntity.setName("总经理");
        GroupEntity groupEntity2 = new GroupEntity();
        groupEntity2.setId("2");
        groupEntity2.setName("部门经理");
        GroupEntity groupEntity3 = new GroupEntity();
        groupEntity3.setId("3");
        groupEntity3.setName("员工");
        identityService.saveGroup(groupEntity);
        identityService.saveGroup(groupEntity2);
        identityService.saveGroup(groupEntity3);
        //    创建用户
        UserEntity u1 = new UserEntity("1");
        u1.setFirstName("Carry");
        u1.setLastName("Dog");
        u1.setEmail("wangwangwang@wang.com");
        u1.setPassword("123456");
        UserEntity u2 = new UserEntity("2");
        u2.setFirstName("Tom");
        u2.setLastName("Cat");
        u2.setEmail("miaomiaomiao@miao.com");
        u2.setPassword("123456");
        UserEntity u3 = new UserEntity("3");
        u3.setFirstName("Jerry");
        u3.setLastName("Mouse");
        u3.setEmail("zhizhizhi@zhi.com");
        u3.setPassword("123456");
        identityService.saveUser(u1);
        identityService.saveUser(u2);
        identityService.saveUser(u3);
        //    存储对应关系【使用id进行对应】
        identityService.createMembership("1", "1");
        identityService.createMembership("2", "2");
        identityService.createMembership("3", "3");


    }


}
