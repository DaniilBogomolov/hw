package ru.itis.processors;

import com.google.auto.service.AutoService;
import lombok.SneakyThrows;
import ru.itis.annotations.HTMLForm;
import ru.itis.annotations.HTMLInput;
import ru.itis.models.Form;
import ru.itis.models.FormParameter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"ru.itis.annotations.HTMLForm", "ru.itis.annotations.HTMLInput"})
public class HTMLProcessor extends AbstractProcessor {

    TemplateProcessor templateProcessor = new TemplateProcessor();

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> formElements = roundEnv.getElementsAnnotatedWith(HTMLForm.class);
        for (Element formElement : formElements) {
            HTMLForm htmlFormData = formElement.getAnnotation(HTMLForm.class); // Get form data
            Form form = Form.builder()
                    .action(htmlFormData.action())
                    .method(htmlFormData.method())
                    .build();
            // Get all nestedElements including the element itself
            List<? extends Element> enclosedElements = formElement.getEnclosedElements();
            for (Element inputElement : enclosedElements) {
                HTMLInput input = inputElement.getAnnotation(HTMLInput.class);
                // if element doesn't contain annotation HTMLInput
                if (input != null) {
                    form.addParameter(FormParameter.builder()
                            .name(input.name())
                            .type(input.type())
                            .placeholder(input.placeholder())
                            .build());
                }
            }
            templateProcessor.processForm(form);
        }
        return true;
    }
}
