package services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import models.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractService {

    protected ConnectionFactory connectionFactory;
    protected final String EXCHANGE_NAME = "documents";
    protected final String QUEUE_NAME = "default";

    public AbstractService() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public abstract void getForUser(UserInfo info);
    protected abstract void generatePdf(ByteArrayOutputStream outputStream);


    protected void getForUser(UserInfo info, String queueName) {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueBind(queueName, EXCHANGE_NAME, "");
            AtomicInteger messagesInQueue = new AtomicInteger(channel.queueDeclarePassive(queueName).getMessageCount());
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                messagesInQueue.getAndDecrement();
                if (message.getProperties().getHeaders().get("user").toString().equals(info.toString())) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.writeBytes(message.getBody());
                    generatePdf(byteArrayOutputStream);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } else {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), true);
                }
            };
            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
            });
            while (true) {
                if (messagesInQueue.get() == 0) {
                    connection.close();
                    return;
                }
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    protected void generatePdf(ByteArrayOutputStream stream, String documentName) {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(documentName));
        Document document = new Document(pdfDocument);
        document.add(new Paragraph(new String(stream.toByteArray())));
        document.close();
        stream.close();
    }

}
