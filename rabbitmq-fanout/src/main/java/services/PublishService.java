package services;

import com.rabbitmq.client.*;
import models.UserInfo;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;


public class PublishService {
    private static final String EXCHANGE_NAME = "documents";
    public static final String EXCHANGE_TYPE = "fanout";

    private UserInfo info;
    private ConnectionFactory connectionFactory;


    public PublishService(UserInfo info) {
        this.info = info;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public void publishAll() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.basicPublish(EXCHANGE_NAME, "",
                    new AMQP.BasicProperties().builder()
                            .headers(Map.of("user", info.toString()))
                            .build()
                    , info.toString().getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
