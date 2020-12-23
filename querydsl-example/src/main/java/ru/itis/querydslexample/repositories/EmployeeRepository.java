package ru.itis.querydslexample.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import ru.itis.querydslexample.models.Employee;
import ru.itis.querydslexample.models.QEmployee;

public interface EmployeeRepository extends MongoRepository<Employee, String>, QuerydslPredicateExecutor<Employee>, QuerydslBinderCustomizer<QEmployee> {

}
