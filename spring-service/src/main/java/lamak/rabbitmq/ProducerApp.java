package lamak.rabbitmq;

import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://guest:guest@localhost:5672/");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        for (int i = 0; i < 10; i++) {
            String message = "Whatsapp " + i;

            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .headers(Map.of("sample", "value"))
                    .build();

            channel.basicPublish("notification", "whatsapp", properties, message.getBytes());
        }

        channel.close();
        connection.close();
    }
}
