import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import utils.HotelReservationUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (keepRunning) {
            try {
                System.out.println("1. Find and reserve a room.");
                System.out.println("2. See my reservations.");
                System.out.println("3. Create an account.");
                System.out.println("4. Admin.");
                System.out.println("5. Exit.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1 || selection == 2 || selection == 3) {
                    String emailAddress = HotelReservationUtils.getUserEmailAddress(scanner);
                    if (!emailAddress.equals("quit")) {
                        Customer currentCustomer = HotelResource.getCustomer(emailAddress);
                        if (currentCustomer != null) {
                            if (selection != 3) {
                                System.out.println("Hi " + currentCustomer.getFirstName() + ", welcome back!");
                                if (selection == 1) {
                                    System.out.println("Which Room Number do you want to Reserve?");
                                    String roomNumber = scanner.nextLine();
                                    IRoom room = HotelResource.getRoom(roomNumber);
                                    if (room != null) {
                                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                        try {
                                            System.out.println("What is the check-in date? Format - 'MM/DD/YYYY'");
                                            String checkInDay = scanner.nextLine();
                                            Date checkInDate = formatter.parse(checkInDay);
                                            System.out.println("What is the check-out date? Format - 'MM/DD/YYYY'");
                                            String checkOutDay = scanner.nextLine();
                                            Date checkOutDate = formatter.parse(checkOutDay);
                                            Reservation reservation = HotelResource.bookARoom(emailAddress, room, checkInDate, checkOutDate);
                                            System.out.println("Successfully Reserved Room - " + roomNumber);
                                            System.out.println("Reservation Confirmation: " + reservation);
                                        } catch (ParseException ex) {
                                            ex.printStackTrace();
                                        }
                                    } else {
                                        System.out.println("Sorry, we don't find this room number. " +
                                                "Please 'Add a room' first in 'Admin'.");
                                    }
                                } else {
                                    System.out.println("Below are all of your reservations.");
                                    System.out.println(HotelResource.getCustomerReservations(emailAddress));
                                }
                            } else {
                                System.out.println("We already have an account related to this email address.");
                            }
                        } else if (selection != 3) {
                            System.out.println("Sorry, we don't find account related to this email address. " +
                                    "Please 'Create an account' before continue.");
                        } else {
                            System.out.println("Please enter your first name.");
                            String firstName = scanner.nextLine();
                            System.out.println("Please enter your last name.");
                            String lastName = scanner.nextLine();
                            HotelResource.createACustomer(emailAddress, firstName, lastName);
                            System.out.println("Your account has been created successfully.");
                            System.out.println("First Name:" + firstName);
                            System.out.println("Last Name:" + lastName);
                            System.out.println("Email:" + emailAddress + "\n");
                        }
                    }
                } else if (selection == 4) {
                    AdminMenu.generateAdminMenu(scanner);
                } else if (selection == 5) {
                    keepRunning = false;
                } else {
                    System.out.println("Please enter a number between 1 and 5.\n");
                }
            } catch (Exception ex) {
                System.out.println("Please enter a number between 1 and 5.\n");
            }
        }
    }
}
