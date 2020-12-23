package ru.itis.querydslexample.services;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.querydslexample.dto.EmployeeDto;
import ru.itis.querydslexample.models.QEmployee;
import ru.itis.querydslexample.repositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAllWithCars(Boolean hasCars) {
        Predicate predicate;
        StringPath carNumber = QEmployee.employee.carNumber;
        if (hasCars) {
            predicate = carNumber.isNotNull().and(carNumber.isNotEmpty());
        } else {
            predicate = carNumber.isNull();
        }
        return StreamSupport.stream(employeeRepository.findAll(predicate).spliterator(), false)
                .map(EmployeeDto::from)
                .collect(Collectors.toList());
    }
}
