package controller.safe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import service.UserService;

/**
 * 幂等实现方案
 */
@RestController
@RequestMapping("safe_md")
public class MiDeng {

    @Autowired
    private UserService userService;

    /**
     *  @Cacheable
     * @param user
     */
    @GetMapping("springCache")
    @Cacheable(value = "midengCache", key = "#id+'_'+#userName")//, condition = "#userName.length()>4"
    //参数说明  value:缓存名称   key:缓存key  condition:缓存条件-返回值为true才进行缓存
    public void update(User user) {
        userService.update(user);
    }


    /**
     * 其他
     */

}
