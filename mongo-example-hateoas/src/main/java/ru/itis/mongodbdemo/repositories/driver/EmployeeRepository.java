package ru.itis.mongodbdemo.repositories.driver;

import ru.itis.mongodbdemo.models.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee entity);
    Employee update(Employee entity);
    void remove(String id);
    Employee findById(String id);
    List<Employee> findAll();
}
