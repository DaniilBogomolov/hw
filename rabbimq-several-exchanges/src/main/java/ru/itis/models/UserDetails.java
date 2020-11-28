package ru.itis.models;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
public class UserDetails {

    private String firstName;
    private String lastName;
    private String passportNumber;
    private String issueDate;
    private int age;

    @Override
    public String toString() {
        return firstName + "_" + lastName + "_" + age + "_" + passportNumber + "_" + issueDate;
    }

    public static UserDetails getFromToString(String string) {
        String[] params = string.split("_");
        return UserDetails.builder()
                .firstName(params[0])
                .lastName(params[1])
                .age(Integer.parseInt(params[2]))
                .passportNumber(params[3])
                .issueDate(params[4])
                .build();
    }
}
