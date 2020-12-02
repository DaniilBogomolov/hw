package ru.itis.mongodbdemo.services;

import ru.itis.mongodbdemo.models.Employee;

public interface EmployeeService {
    Employee updateStatus(String id, Employee.Status newStatus);
}
