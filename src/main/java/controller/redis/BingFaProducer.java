package controller.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bf")
public class BingFaProducer {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("b1")
    public void b1(int n) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < n) {
                    redisTemplate.convertAndSend("msg", "b1");
                    i++;
                }
            }
        }, "t1");

        thread.start();
        System.out.println("---------->t1 生产完成");

    }

    @GetMapping("b2")
    public void b2(int n) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < n) {
                    redisTemplate.convertAndSend("msg", "b2");
                    i++;
                }
            }
        }, "t2");


        thread.start();
        System.out.println("---------->t2 生产完成");


    }

    @GetMapping("b3")
    public void b3(int n) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < n) {
                    redisTemplate.convertAndSend("msg", "b3");
                    i++;
                }
            }
        }, "t3");

        thread.start();

        System.out.println("---------->t3 生产完成");
    }

}
