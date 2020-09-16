package models;

import lombok.*;

@AllArgsConstructor
@Builder
public class UserInfo {

    private String firstName;
    private String lastName;
    private String passport;
    private int age;
    private String passportDate;

    @Override
    public String toString() {
        return firstName + lastName + age + passport + passportDate;
    }
}
