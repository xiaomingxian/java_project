package controller.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import pojo.redis.Msg;

@Component("sub4")
public class RedisCustomer implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }
        String s = new String(message.getBody());
        System.out.println("channel:" + new String(message.getChannel())
                + ",message:" + s);

        Msg msg = JSON.parseObject(s, Msg.class);
        //System.out.println(msg);
    }
}
