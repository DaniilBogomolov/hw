package services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.SneakyThrows;
import models.UserInfo;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

public class InsuranceService extends AbstractService {

    public static final String QUEUE_NAME = "insurance_queue";

    @Override
    public void getForUser(UserInfo info) {
        super.getForUser(info, QUEUE_NAME);
    }

    @SneakyThrows
    @Override
    protected void generatePdf(ByteArrayOutputStream stream) {
        super.generatePdf(stream, new Date().toString() + UUID.randomUUID().toString() + ":insurance.pdf");
    }

}
