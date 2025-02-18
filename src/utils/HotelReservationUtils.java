package utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class HotelReservationUtils {
    public static String getUserEmailAddress(Scanner scanner) {
        System.out.println("Please enter your email address.");
        String emailAddress = scanner.nextLine();
        boolean validEmailAddressReceived = false;
        while (!validEmailAddressReceived) {
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|net|org)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(emailAddress).matches() && !emailAddress.equals("quit")) {
                System.out.println("Please enter a valid email address. ('quit' - return to main menu)");
                emailAddress = scanner.nextLine();
            } else {
                validEmailAddressReceived = true;
            }
        }
        return emailAddress;
    }
}
