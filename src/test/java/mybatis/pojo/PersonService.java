package mybatis.pojo;


import mybatis.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.User;

import java.util.List;

@Component
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public void select() {
        List<User> select = personMapper.select1();
        System.out.println(select);
    }

}
