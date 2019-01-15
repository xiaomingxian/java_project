package controller.project_test;

import dao.TestMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Test;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("code")
public class CodeDemo {

    @Resource
    private TestMapper testMapper;

    @RequestMapping("getData")
    public String getData(String data) {
        System.out.println("------------>" + data);
        Test test = new Test();
        test.setInfo(data);
        test.setCreateTime(new Date());
        test.setStatus(1);
        testMapper.insert(test);
        return data;
    }

}
