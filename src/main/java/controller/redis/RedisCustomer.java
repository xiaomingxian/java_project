package controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component("sub4")
public class RedisCustomer implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String s = new String(message.getBody());
        String s1 = new String(message.getChannel());
        //System.out.println("channel:" + new String(message.getChannel())
        //        + ",message:" + s);
        try {

            //Msg msg = JSON.parseObject(s, Msg.class);
            //System.out.println(s1 + "----" + msg);
            System.out.println(s1 + "----" + s);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String s = "{\"@class\":\"cn.com.cintel.rg.external_interface.model.PolicyInfoSetModel\",\"policysetid\":\"11\",\"serialnumber\":\"c6848138-0f14-4791-85a8-468b8aef955f\",\"policyinfo\":[\"java.util.ArrayList\",[]],\"domain\":1}";

        s = s.replace("\"java.util.ArrayList\",", "");
        String start = "\"policyinfo\":";
        String rep = s.substring(s.indexOf(start) + start.length(), s.indexOf("],") + 1);
        String substring = rep.substring(1, rep.length() - 1);
        substring = substring.replace("[", "{");
        substring = substring.replace("]", "}");
        substring = "[" + substring + "]";
        System.out.println(substring);


    }
}
