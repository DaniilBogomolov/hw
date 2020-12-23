package ru.itis.querydslexample.services;

import com.querydsl.core.types.Predicate;
import ru.itis.querydslexample.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAllWithCars(Boolean hasCars);
}
