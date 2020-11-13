package ru.itis.services.impl.publishers;

import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;
import ru.itis.services.AbstractConsumer;
import ru.itis.services.AbstractPublisher;

@Component("*")
public class FanoutPublisher extends AbstractPublisher {

    public static final String ROUTING_KEY = "";
    public static final String EXCHANGE_NAME = "documents_fanout_exchange";
    public static final String EXCHANGE_TYPE = "fanout";

    @Override
    public void publish(UserDetails info) {
        super.publish(ROUTING_KEY, EXCHANGE_NAME, EXCHANGE_TYPE, info);
    }
}
