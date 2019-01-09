package service.impl;

import dao.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;
import pojo.UserExample;
import service.UserService;

import javax.annotation.Resource;
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
}
