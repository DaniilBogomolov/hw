package services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import models.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class PassportService extends AbstractService {
    public static final String QUEUE_NAME = "passport_queue";

    public PassportService() {
        super();
    }

    @Override
    public void getForUser(UserInfo info) {
        super.getForUser(info, QUEUE_NAME);
    }

    @SneakyThrows
    @Override
    protected void generatePdf(ByteArrayOutputStream stream) {
        super.generatePdf(stream, new Date().toString() + UUID.randomUUID().toString() + ":passport.pdf");
    }
}
