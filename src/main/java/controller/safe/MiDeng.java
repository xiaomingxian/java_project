package controller.safe;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pojo.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

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


    @GetMapping("saveSeesion")
    public void save(String val) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        HashSet hashSet = new HashSet();
        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        hashSet.add("4");
        hashSet.add(val);
        session.setAttribute("val_key", hashSet);
    }

    @GetMapping("get")
    public HashSet get() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        HashSet val_key = (HashSet) session.getAttribute("val_key");
        return val_key;
    }

    /**
     * ehcache测试
     */
    @GetMapping("eh")
    public void eh() {
        // Create a cache manager 创建缓存管理者
        final CacheManager cacheManager = new CacheManager();
        // create the cache called "helloworld"  引用ehcache.xml申明的的名为helloworld的缓存创建Cache对象
        final Cache cache = cacheManager.getCache("hello");
        // create a key to map the data to
        final String key = "greeting";
        // Create a data element
        final Element putGreeting = new Element(key, "Hello,World!");
        // Put the element into the data store  //将map对象放到cache缓存里
        cache.put(putGreeting);
        // Retrieve the data element  //从cache对象中获得到元素
        final Element getGreeting = cache.get(key);
        // Retrieve the data element
        System.out.println(getGreeting.getObjectValue());


    }
}
