package service;

import pojo.Test;
import utils.mypagehelper.MyPageHelper;

import java.util.List;

public interface TestService {
    List<Test> getAll();

    int getCount(Test test);

    List<Test> selectByMyPageHelper(MyPageHelper<Test> testMyPageHelper);

}
