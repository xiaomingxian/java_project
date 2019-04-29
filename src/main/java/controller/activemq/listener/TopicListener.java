package controller.activemq.listener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class TopicListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("------------------->topic1监听");
        // 判断消息类型是TextMessage
        if (message instanceof ActiveMQTextMessage) {
            // 如果是，则进行强转
            try {
                TextMessage textMessage = (ActiveMQTextMessage) message;
                // 消费消息，打印消息内容
                String text = textMessage.getText();
                System.out.println("------------------->TopicMessageListener1-消费者1消息监听器接收到消息；消息内容为：" + text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } if (message instanceof ActiveMQMapMessage) {
            // 如果是，则进行强转
            try {
                ActiveMQMapMessage textMessage = (ActiveMQMapMessage) message;
                // 消费消息，打印消息内容
                System.out.println("------------------->TopicMessageListener1-消费者1消息监听器接收到消息；map类型：" + textMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
