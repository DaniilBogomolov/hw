package ru.itis.services;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.engines.ISAACEngine;
import ru.itis.models.UserDetails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractConsumer {

    protected ConnectionFactory connectionFactory;
    protected final String EXCHANGE_NAME = "medical_exchange";
    protected final String QUEUE_NAME = "default";

    public AbstractConsumer() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public abstract void getForUser(UserDetails info);
    protected abstract PdfDocument generateDocument();
    protected abstract void fillForm(Map<String, PdfFormField> fields, PdfFont font, UserDetails details);

    protected void getForUser(UserDetails info, String queueName) {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            AtomicInteger messagesInQueue = new AtomicInteger(channel.queueDeclarePassive(queueName).getMessageCount());
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                if (message.getProperties().getHeaders().get("user").toString().equals(info.toString())) {
                    generatePdf(new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } else {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), true);
                }
                messagesInQueue.getAndDecrement();
                if (messagesInQueue.get() == 0) {
                    try {
                        channel.close();
                    } catch (TimeoutException e) {
                        throw new IllegalStateException(e);
                    }
                }
            };
            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    private void generatePdf(String userToString) {
        PdfDocument document = generateDocument();
        UserDetails details = UserDetails.getFromToString(userToString);
        PdfAcroForm acroForm = PdfAcroForm.getAcroForm(document, false);
        Map<String, PdfFormField> formFields = acroForm.getFormFields();
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
        fillForm(formFields, font, details);
        document.close();
    }

//    @SneakyThrows
//    protected void generatePdf(String userToString, String docTemplate, String documentName) {
//        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(documentName));
//        Document document = new Document(pdfDocument);
//        document.add(new Paragraph(new String(stream.toByteArray())));
//        document.close();
//        stream.close();
//    }

}