package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.services.AbstractConsumer;

import java.util.Arrays;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReceiveController {

    private Map<String, AbstractConsumer> consumers;
    private SignInController signInController;

    public void sendRequest(String command) {
        if (signInController.isSignedIn()) {
            Arrays.stream(command.substring(4).trim().split(" "))
                    .distinct()
                    .map("g"::concat)
                    .forEach(str -> {
                        if (consumers.containsKey(str)) {
                            consumers.get(str).getForUser(signInController.getUserDetails());
                        } else if (str.equals("g*")) {
                            consumers.values().forEach(c -> c.getForUser(signInController.getUserDetails()));
                        }
                    });
        }
    }
}
