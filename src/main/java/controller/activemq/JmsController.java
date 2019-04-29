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

    //线程池
    private ExecutorService pool = Executors.newFixedThreadPool(10);

    @GetMapping("queueProduce")
    public String queueProduce() {

        for (int i = 0; i < 20; i++) {
            pool.submit(() -> {
                HashMap<String, String> map = new HashMap<>();
                String name = Thread.currentThread().getName();
                map.put(name, "queue:" + name);
                jmsQueueTemplate.convertAndSend("test.queue", map.toString());
            });
        }
        return "queue msg create success";
    }

    @GetMapping("topicProduce")
    public String topicProduce() {
        for (int i = 0; i < 20; i++) {
            pool.submit(() -> {
                HashMap<String, String> map = new HashMap<>();
                String name = Thread.currentThread().getName();
                map.put(name, "queue:" + name);
                jmsTopicTemplate.convertAndSend("test.topic", map.toString());
            });
        }
        return "topic msg create success";
    }

}
