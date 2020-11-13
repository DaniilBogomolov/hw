package ru.itis.services.impl.publishers;

import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;
import ru.itis.services.AbstractPublisher;

import java.util.List;

@Component("medical")
public class MedicalPublisher extends AbstractPublisher {

    private final List<String> ROUTINGS = List.of("department.insurance.medical", "department.insurance.property");
    public static final String EXCHANGE_NAME = "medical_exchange";
    public static final String EXCHANGE_TYPE = "topic";


    @Override
    public void publish(UserDetails info) {
        ROUTINGS.forEach(routingKey -> super.publish(routingKey, EXCHANGE_NAME, EXCHANGE_TYPE, info));
    }

    public void publishWithRoutingKey(UserDetails info, String key) {
        super.publish(key, EXCHANGE_NAME, EXCHANGE_TYPE, info);
    }
}
