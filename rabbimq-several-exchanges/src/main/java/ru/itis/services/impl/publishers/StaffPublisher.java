package ru.itis.services.impl.publishers;

import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;
import ru.itis.services.AbstractPublisher;

@Component("staff")
public class StaffPublisher extends AbstractPublisher {

    public static String ROUTING_KEY = "department.staff.*";
    public static final String EXCHANGE_NAME = "staff_exchange";
    public static final String EXCHANGE_TYPE = "topic";


    @Override
    public void publish(UserDetails info) {
        super.publish(ROUTING_KEY, EXCHANGE_NAME, EXCHANGE_TYPE, info);
    }
}
