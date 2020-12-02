package ru.itis.mongodbdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.mongodbdemo.controllers.EmployeeController;
import ru.itis.mongodbdemo.models.Employee;
import ru.itis.mongodbdemo.models.Employee.Status;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
@RequiredArgsConstructor
public class EmployeeRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> process(EntityModel<Employee> model) {
        Employee employee = model.getContent();
        if (employee != null) {
            Employee.Status status = employee.getStatus();
            if (Status.WORKING.equals(status)) {
                model.add(linkTo(methodOn(EmployeeController.class).updateSalary(employee.get_id(), Status.VACATION))
                        .withRel("sendOnVacation"));
                model.add(linkTo(methodOn(EmployeeController.class).updateSalary(employee.get_id(), Status.FIRED))
                        .withRel("fire"));
            } else if (Status.VACATION.equals(status)) {
                model.add(linkTo(methodOn(EmployeeController.class).updateSalary(employee.get_id(), Status.WORKING))
                        .withRel("returnToWork"));
            }
        }
        return model;
    }
}
