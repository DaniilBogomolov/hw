package ru.itis.mongodbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.mongodbdemo.models.Employee;
import ru.itis.mongodbdemo.repositories.driver.EmployeeRepositoryDriverImpl;
import ru.itis.mongodbdemo.repositories.spring.EmployeeRepository;

@SpringBootApplication
public class MongoDbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbDemoApplication.class, args);
    }

}
