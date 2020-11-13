package ru.itis.services;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.models.UserDetails;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Component
public class PublishService {

    public static final String ROUTING_KEY = "department.insurance.*";
    private static final String EXCHANGE_NAME = "medical_exchange";
    public static final String EXCHANGE_TYPE = "topic";

    private ConnectionFactory connectionFactory;

    public PublishService() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

//    public PublishService(UserDetails info) {
//        this.info = info;
//        connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("localhost");
//    }

    public void publishAll(UserDetails info) {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true);
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,
                    new AMQP.BasicProperties().builder()
                            .headers(Map.of("user", info.toString()))
                            .build()
                    , info.toString().getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
