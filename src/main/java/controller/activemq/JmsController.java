package controller.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("jms")
public class JmsController {

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Resource(name = "jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;


    @GetMapping("queueProduce")
    public String queueProduce() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            pool.submit(() -> {
                HashMap<String, String> map = new HashMap<>();
                String name = Thread.currentThread().getName();
                map.put(name, "queue:" + name);
                jmsQueueTemplate.convertAndSend("test.queue", map);
            });
        }
        return "queue msg create success";
    }

    @GetMapping("topicProduce")
    public String topicProduce() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            pool.submit(() -> {
                HashMap<String, String> map = new HashMap<>();
                String name = Thread.currentThread().getName();
                map.put(name, "queue:" + name);
                jmsQueueTemplate.convertAndSend("test.topic", map);
            });
        }
        return "topic msg create success";
    }

}
