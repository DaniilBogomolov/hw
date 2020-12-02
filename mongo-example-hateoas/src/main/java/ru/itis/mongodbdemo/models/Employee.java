package ru.itis.mongodbdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "employee")
public class Employee {

    @MongoId
    private String _id;
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    private Integer salary;
    @NotNull
    private Status status;

    public enum Status {
        WORKING, VACATION, FIRED
    }
}
