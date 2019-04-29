package controller.activemq.listener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class QueueListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("---------->queue-2-开始监听:");
        if (message instanceof ActiveMQTextMessage) {
            // 如果是，则进行强转
            TextMessage textMessage = (ActiveMQTextMessage) message;
            try {
                // 消费消息，打印消息内容
                String text = textMessage.getText();
                System.out.println("---------->queue-2-监听到的消息:" + text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (message instanceof ActiveMQMapMessage) {
            // 如果是，则进行强转
            MapMessage textMessage = (ActiveMQMapMessage) message;
            try {
                // 消费消息，打印消息内容
                System.out.println("---------->queue-2-监听到的消息:" + textMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
