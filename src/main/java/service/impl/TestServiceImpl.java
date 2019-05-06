package service.impl;


import dao.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Test;
import service.TestService;
import utils.mypagehelper.MyPageHelper;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {


    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> getAll() {


        return testMapper.getAll();
    }

    @Override
    public int getCount(Test test) {
        return testMapper.getCount(test);
    }

    @Override
    public List<Test> selectByMyPageHelper(MyPageHelper<Test> testMyPageHelper) {
        return testMapper.selectByMyPageHelper(testMyPageHelper);
    }

}
