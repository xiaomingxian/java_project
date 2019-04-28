package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class T2_Topic {
    /**
     * 生产
     *
     * @throws Exception
     */
    @Test
    public void testProducerTopic() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.37.129:61616/");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        Message message = session.createTextMessage("hello topic message1");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 消费1
     *
     * @throws Exception
     */
    @Test
    public void testConsumerTopic() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.37.129:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("the first consumer...");
                        System.out.println(textMessage.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 消费2
     *
     * @throws Exception
     */
    @Test
    public void testConsumerTopic2() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.37.129:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("the secend consumer...");
                        System.out.println(textMessage.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

}
