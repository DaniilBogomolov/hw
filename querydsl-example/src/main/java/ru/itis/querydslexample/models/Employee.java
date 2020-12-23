package ru.itis.querydslexample.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Document(collection = "employee")
public class Employee {
    @Id
    @MongoId
    private String id;
    private String firstName;
    private String lastName;
    private String carNumber;
    private LocalDate dateOfBirth;
}
