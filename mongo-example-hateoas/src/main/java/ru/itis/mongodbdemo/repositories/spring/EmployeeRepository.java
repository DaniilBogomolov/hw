package ru.itis.mongodbdemo.repositories.spring;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongodbdemo.models.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
