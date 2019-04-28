package controller.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("---------->queue-1-开始监听:");


        TextMessage tm = (TextMessage) message;
        try {
            String text = tm.getText();
            System.out.println("---------->queue-1-监听到的消息:" + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
