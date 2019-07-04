package controller.mybatis;

import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mybatis")
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("for")
    public String forSearch() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        List<User> users = userMapper.forSearch(ids);
        return "";

    }


}
