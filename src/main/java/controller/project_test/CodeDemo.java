package controller.project_test;

import dao.TestMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("bigData")
    public Map getBigData() {
        HashMap<Object, Object> map = new HashMap<>();
        int count = testMapper.getCount(null);
        List<Test> all = testMapper.getAll();
        map.put("count", count);
        map.put("data", all);

        return map;
    }

}
