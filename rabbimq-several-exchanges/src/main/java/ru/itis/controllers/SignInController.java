package ru.itis.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.itis.models.UserDetails;

@Component
public class SignInController {

    private UserDetails userDetails;

    public void signIn(String command) {
        String[] info = command.substring(8).split("_");
        userDetails = UserDetails.builder()
                .firstName(info[0])
                .lastName(info[1])
                .passportNumber(info[2])
                .issueDate(info[3])
                .age(Integer.parseInt(info[4]))
                .build();
    }
    Thread

    public boolean isSignedIn() {
        return userDetails != null;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
