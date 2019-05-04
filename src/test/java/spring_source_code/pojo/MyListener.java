package spring_source_code.pojo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    @EventListener(classes = {ApplicationEvent.class})
    public void mylistener(ApplicationEvent applicationEvent) {
        System.out.println("-------注解监听器:" + applicationEvent);
    }

}
