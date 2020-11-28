package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.services.HelpService;

@Component
@AllArgsConstructor
public class HelpController {

    private HelpService helpService;

    public void processHelp() {
        helpService.printHelp();
    }

    public void processError(String error) {
        System.out.println(error);
    }
}
