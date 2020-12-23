package ru.itis.querydslexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.querydslexample.models.Employee;
import ru.itis.querydslexample.repositories.EmployeeRepository;

import java.time.LocalDate;

@SpringBootApplication
public class QuerydslExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerydslExampleApplication.class, args);
    }

}
