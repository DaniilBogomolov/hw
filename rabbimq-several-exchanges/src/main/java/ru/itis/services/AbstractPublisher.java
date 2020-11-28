package ru.itis.services;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.models.UserDetails;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public abstract class AbstractPublisher {

    private ConnectionFactory connectionFactory;

    public AbstractPublisher() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public abstract void publish(UserDetails info);

    public void publish(String routingKey, String exchangeName, String exchangeType, UserDetails info) {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish(exchangeName, routingKey,
                    new AMQP.BasicProperties().builder()
                            .headers(Map.of("user", info.toString()))
                            .build()
                    , info.toString().getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
