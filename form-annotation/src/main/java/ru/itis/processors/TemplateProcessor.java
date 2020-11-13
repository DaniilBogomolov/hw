package ru.itis.processors;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import ru.itis.models.Form;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TemplateProcessor {

    private final Configuration configuration;
    private static final String FORM_TEMPLATE_NAME = "form_template.ftl";
    public static final String FORM_OUTPUT_NAME = "generated_form.html";

    @SneakyThrows
    public TemplateProcessor() {
        configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates/"));
    }

    @SneakyThrows
    public void processForm(Form form) {
        Template template = configuration.getTemplate(FORM_TEMPLATE_NAME);
        Map<String, Object> data = new HashMap<>();
        data.put("method", form.getMethod());
        data.put("action", form.getAction());
        data.put("inputs", form.getParameters());
        template.process(data, new FileWriter(LocalDate.now().toString() + ": " + FORM_OUTPUT_NAME));
    }
}
