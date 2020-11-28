package ru.itis.controllers;

import org.springframework.stereotype.Component;

@Component
public class ExitController {

    public void exit() {
        System.exit(0);
    }
}
