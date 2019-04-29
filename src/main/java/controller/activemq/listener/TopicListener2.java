package controller.activemq.listener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class TopicListener2 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("------------------->topic2监听");
        // 判断消息类型是TextMessage
        if (message instanceof ActiveMQTextMessage) {
            // 如果是，则进行强转
            TextMessage textMessage = (ActiveMQTextMessage) message;
            try {
                // 消费消息，打印消息内容
                String text = textMessage.getText();
                System.out.println("------------------->TopicMessageListener2-消费者2消息监听器接收到消息；消息内容为：" + text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (message instanceof ActiveMQMapMessage) {
            // 如果是，则进行强转
            ActiveMQMapMessage textMessage = (ActiveMQMapMessage) message;
            try {
                // 消费消息，打印消息内容
                System.out.println("------------------->TopicMessageListener2-消费者2消息监听器接收到消息；map类型：" + textMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
