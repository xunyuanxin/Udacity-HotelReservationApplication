package model;

import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;

    public Customer(String firstName, String lastName, String email) {
            String emailRegex = "^(.+)@(.+).(com|net|org)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(email).matches()) {
                throw new IllegalArgumentException("Error: Invalid Email");
            }
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "First Name:" + firstName + ", Last Name:" + lastName + ", Email:" + email;
    }
}
