package controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.redis.Msg;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;


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

    @GetMapping("scan")
    public void test() {
        Set<Object> execute = redisTemplate.execute(new RedisCallback<Set<Object>>() {

            @Override
            public Set<Object> doInRedis(RedisConnection connection) throws DataAccessException {

                Set<Object> binaryKeys = new HashSet<>();

                Cursor<byte[]> cursor = connection.scan( new ScanOptions.ScanOptionsBuilder().match("*_intercept_0_2019*").count(1000).build());
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next()));
                }
                return binaryKeys;
            }
        });


        ////JedisCluster jedisCluster = jedisClusterFactory.getJedisCluster();
        //Jedis jedisCluster = jedisPool.getResource();
        //
        //long sta2 = System.currentTimeMillis();
        //ScanParams scanParams = new ScanParams();
        //scanParams.match("{" + like + "}");
        //ScanResult<String> scan = jedisCluster.scan("0", scanParams);
        //String stringCursor = scan.getStringCursor();
        //ScanResult<String> scan1 = jedisCluster.scan(stringCursor);
        //System.out.println(scan1);
        //long end2 = System.currentTimeMillis();
        //
        //List<String> result1 = scan.getResult();
        //System.out.println("---scan 查询--->" + result1.size() + " 耗时：" + (end2 - sta2));
        //System.out.println("---scan 查询--->" + result1);
    }


}
