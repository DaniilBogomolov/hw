package ru.itis.mongodbdemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.mongodbdemo.models.Employee;
import ru.itis.mongodbdemo.services.EmployeeService;

@RepositoryRestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PutMapping("/employees/{userId}/update/{status}")
    public ResponseEntity<Employee> updateSalary(@PathVariable String userId,
                                                 @PathVariable Employee.Status status) {
        return ResponseEntity.ok(employeeService.updateStatus(userId, status));
    }
}
