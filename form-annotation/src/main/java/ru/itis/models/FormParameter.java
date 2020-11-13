package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormParameter {
    private String name;
    private String type;
    private String placeholder;
}
