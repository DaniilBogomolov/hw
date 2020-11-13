package ru.itis.services.impl.consumers;

import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;
import ru.itis.services.AbstractConsumer;
import ru.itis.services.AbstractPublisher;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

@Component("gmedical_insurance")
public class MedicalInsuranceConsumer extends AbstractConsumer {

    public static final String QUEUE_NAME = "medical_insurance_queue";

    @Override
    public void getForUser(UserDetails info) {
        super.getForUser(info, QUEUE_NAME);
    }

    @Override
    protected void generatePdf(ByteArrayOutputStream outputStream) {
        super.generatePdf(outputStream, new Date().toString() + UUID.randomUUID().toString() + ":medical.pdf");
    }
}
