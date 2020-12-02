package ru.itis.mongodbdemo.repositories.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.itis.mongodbdemo.models.Employee;
import ru.itis.mongodbdemo.repositories.driver.EmployeeRepository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryJpaImpl implements EmployeeRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Employee save(Employee entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public Employee update(Employee entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public void remove(String id) {
        mongoTemplate.findAndRemove(new Query(
                where("id").is(id)
        ), Employee.class);
    }

    @Override
    public Employee findById(String id) {
        return mongoTemplate.findById(id, Employee.class);
    }

    @Override
    public List<Employee> findAll() {
        return mongoTemplate.findAll(Employee.class);
    }
}
