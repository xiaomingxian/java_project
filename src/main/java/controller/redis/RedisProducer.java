package controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.redis.Msg;

import java.util.NoSuchElementException;
import java.util.UUID;


@RestController
@RequestMapping("redis")
public class RedisProducer {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("produceMsg")
    public String produceMsg(Integer n) {
        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            Msg msg = new Msg(uuid + "", "tom" + uuid, "msg:" + uuid);
            redisTemplate.convertAndSend("msg", msg);
            redisTemplate.convertAndSend("mec", msg);
        }
        return "produce msg success";
    }


    @GetMapping("listSet")
    public String listSet(Integer n) {

        for (int i = 0; i < n; i++) {
            UUID uuid = UUID.randomUUID();
            Msg msg = new Msg(uuid + "", "tom" + i, "msg:" + uuid);
            //redisTemplate.expire("list",-1)
            Long aLong = redisTemplate.opsForList().leftPush("list", msg);
        }

        return "success";
    }

    @GetMapping("rightGet")
    public String rightGet() {
        Object list = null;
        Msg msg = null;
        try {
            list = redisTemplate.opsForList().rightPop("list");
            System.out.println(list.toString());
            msg = (Msg) list;
        } catch (NoSuchElementException exception) {
            return "无信息";
        } catch (NullPointerException e) {
            return "无信息";
        } catch (Exception ex) {

        }

        return "success" + msg;
    }


}
