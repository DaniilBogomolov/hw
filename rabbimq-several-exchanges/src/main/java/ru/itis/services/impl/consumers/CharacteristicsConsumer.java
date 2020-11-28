package ru.itis.services.impl.consumers;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;
import ru.itis.services.AbstractConsumer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component("gstaff_characteristics")
public class CharacteristicsConsumer extends AbstractConsumer {

    public static final String QUEUE_NAME = "characteristics_staff_queue";
    public static final String PDF_TEMPLATE_NAME = "characteristics.pdf";


    @Override
    public void getForUser(UserDetails info) {
        super.getForUser(info, QUEUE_NAME);
    }

    @SneakyThrows
    @Override
    protected PdfDocument generateDocument() {
        return new PdfDocument(new PdfReader(PDF_TEMPLATE_NAME),
                new PdfWriter(new FileOutputStream("documents/" + new Date().toString() + UUID.randomUUID().toString() + ":characteristics.pdf")));
    }

    @Override
    protected void fillForm(Map<String, PdfFormField> formFields, PdfFont font, UserDetails details) {
        formFields.get("firstName").setValue(details.getFirstName()).setFont(font);
        formFields.get("lastName").setValue(details.getLastName()).setFont(font);
    }
}
