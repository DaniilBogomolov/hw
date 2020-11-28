package ru.itis.services.impl;

import org.springframework.stereotype.Component;
import ru.itis.services.HelpService;

@Component
public class HelpServiceImpl implements HelpService {

    @Override
    public void printHelp() {
        System.out.println("Help:");
        System.out.println("To sign in use: \"SignIn: firstName_lastName_passportInfo_issueDate_age\"");
        System.out.println("To send request use: \"Send: [parameters]\"");
        System.out.println("\tList of available parameters (all other will be ignored):\n" +
                "\t\t * - all services, medical [medical_property, medical_insurance], staff");
        System.out.println("To exit use: \"Exit\"");
    }
}
