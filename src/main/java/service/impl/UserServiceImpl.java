package service.impl;

import dao.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;
import pojo.UserExample;
import service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper lUserMapper;


    @Override
    public void register(User lUser) {

        lUserMapper.insert(lUser);

        // int x = 1 / 0;


    }

    @Override
    public List<User> getAll(UserExample userExample) {

        List<User> users = lUserMapper.selectByExample(userExample);


        return users;
    }

    @Override
    public void update(User user) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setCreateTime(new Date());
        user1.setPassword("123456");
        user1.setPhone(user.getPhone());
        user1.setEmail("safe-test@qq.com");
        user1.setUpdateTima(new Date());
        user1.setUserName("safe-md");
        lUserMapper.updateByPrimaryKey(user1);
    }
}
