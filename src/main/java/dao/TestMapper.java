package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Test;
import utils.mypagehelper.MyPageHelper;

import java.util.List;

public interface TestMapper {
    List<Test> getAll();

    int getCount(Test test);

    List<Test> selectByMyPageHelper(@Param("p") MyPageHelper<Test> testMyPageHelper);

    void insert(Test test);
}
