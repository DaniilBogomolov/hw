package ru.itis.services.impl.publishers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;
import ru.itis.services.AbstractPublisher;

@Component("medical_insurance")
@AllArgsConstructor
public class MedicalInsurancePublisher extends AbstractPublisher {

    public static final String ROUTING_KEY = "department.insurance.medical";
    private MedicalPublisher medicalPublisher;

    @Override
    public void publish(UserDetails info) {
        medicalPublisher.publishWithRoutingKey(info, ROUTING_KEY);
    }
}
