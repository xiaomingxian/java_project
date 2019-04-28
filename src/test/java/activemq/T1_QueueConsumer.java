package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class T1_QueueConsumer {


    /**
     * 点对点接收消息
     * @throws Exception
     */
    @Test
    public void testQueueConsumer() throws Exception{
        //1、创建一个 ConnectionFactory 对象连接 MQ 服务器
        String brokerURL = "tcp://192.168.37.129:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        //2、创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用 Connection 对象 创建一个 Session 对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、创建一个 Destination 对象。queue 对象
        Queue queue = session.createQueue("test.queue");
        //6、使用 Session 对象创建一个消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //7、接收消息
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                //8、打印结果
                TextMessage textMessage = (TextMessage) message;

                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });

        //9、等待接收消息。( 接收到消息后才网下面执行。关闭资源 )
        System.in.read();
        //10、关闭资源
        consumer.close();
        session.close();
        connection.close();

    }

}
