package ru.itis.querydslexample.controllers;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.querydslexample.dto.EmployeeDto;
import ru.itis.querydslexample.models.Employee;
import ru.itis.querydslexample.services.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees/search")
    public ResponseEntity<List<EmployeeDto>> findAllByCars(@RequestParam("has_cars") Boolean hasCars) {
        return ResponseEntity.ok(employeeService.findAllWithCars(hasCars));
    }
}
