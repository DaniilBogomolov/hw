package ru.itis.models;

import ru.itis.annotations.HTMLForm;
import ru.itis.annotations.HTMLInput;

@HTMLForm(method = "post", action = "/users")
public class User {
    @HTMLInput(name = "firstName", placeholder = "Your first name")
    private String firstName;
    @HTMLInput(name = "lastName", placeholder = "Your last name")
    private String lastName;
    @HTMLInput(type = "email", name = "email", placeholder = "Your email")
    private String email;
    @HTMLInput(type = "password", name = "password", placeholder = "Your password")
    private String password;
}
