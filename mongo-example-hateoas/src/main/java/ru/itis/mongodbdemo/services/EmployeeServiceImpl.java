package ru.itis.mongodbdemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.mongodbdemo.models.Employee;
import ru.itis.mongodbdemo.repositories.spring.EmployeeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee updateStatus(String id, Employee.Status newStatus) {
        Optional<Employee> employeeCandidate = employeeRepository.findById(id);
        if (employeeCandidate.isPresent()) {
            Employee employee = employeeCandidate.get();
            employee.setStatus(newStatus);
            return employeeRepository.save(employee);
        } throw new IllegalStateException("No user found!");
    }
}
