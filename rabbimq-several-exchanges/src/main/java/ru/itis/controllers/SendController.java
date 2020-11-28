package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.services.AbstractPublisher;

import java.util.Arrays;
import java.util.Map;

@Component
@AllArgsConstructor
public class SendController {

    private Map<String, AbstractPublisher> publishers;
    private SignInController signInController;

    public void sendRequest(String command) {
        if (signInController.isSignedIn()) {
            Arrays.stream(command.substring(5).trim().split(" "))
                    .distinct()
                    .forEach(str -> {
                        if (publishers.containsKey(str)) {
                            publishers.get(str).publish(signInController.getUserDetails());
                        }
                    });
        }
    }
}
