package ru.itis.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Form {
    private String method;
    private String action;
    private List<FormParameter> parameters;

    public void addParameter(FormParameter parameter) {
        if (parameters == null) parameters = new ArrayList<>();
        parameters.add(parameter);
    }
}
