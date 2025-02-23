import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static void generateAdminMenu(Scanner scanner) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                System.out.println("1. View all Customers.");
                System.out.println("2. View all Rooms.");
                System.out.println("3. View all Reservations.");
                System.out.println("4. Add a Room.");
                System.out.println("5. Back to Main Menu.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1) {
                    System.out.println("Below are all the customers we have: ");
                    System.out.println(AdminResource.getAllCustomers());
                } else if (selection == 2) {
                    System.out.println("Below are all the rooms we have: ");
                    System.out.println(AdminResource.getAllRooms());
                } else if (selection == 3) {
                    AdminResource.displayAllReservations();
                } else if (selection == 4) {
                    System.out.println("Which Room Number do you want to add?");
                    System.out.println("Here're all the Rooms we've already have: " + AdminResource.getAllRooms());
                    System.out.println("quit - Cancel creating new room");
                    boolean roomNumberRunning = true;
                    String roomNumber = "";
                    while (roomNumberRunning) {
                        roomNumber = scanner.nextLine();
                        if (isIntegerString(roomNumber)) {
                            if (AdminResource.getAllRoomNumbers().contains(roomNumber)) {
                                System.out.println("Please enter another room number, room number - " + roomNumber + " has already existed.");
                            } else {
                                roomNumberRunning = false;
                                System.out.println("The Room Number - " + roomNumber + "has been created successfully.");
                            }
                        } else if (roomNumber.equals("quit")) {
                            keepRunning = false;
                            break;
                        } else {
                            System.out.println("Please enter an integer for new room number.");
                            System.out.println("Here're all the Rooms we've already have: " + AdminResource.getAllRooms());
                            System.out.println("Type 'quit' to cancel adding Room Number - \" + roomNumber + \".\"");
                        }
                    }
                    if (!keepRunning) {
                        break;
                    }
                    System.out.println("What Room Type do you want to set for Room Number - " + roomNumber + "?");
                    System.out.println("1 - SINGLE");
                    System.out.println("2 - DOUBLE");
                    System.out.println("quit - Cancel creating new room");
                    RoomType roomType = null;
                    String roomTypeString = "";
                    boolean roomTypeRunning = true;
                    while (roomTypeRunning) {
                        roomTypeString = scanner.nextLine();
                        int roomTypeIndicator = Integer.parseInt(roomTypeString);
                        if (roomTypeIndicator == 1) {
                            roomType = RoomType.SINGLE;
                            roomTypeString = "SINGLE";
                            roomTypeRunning = false;
                            System.out.println("The Room Type for Room Number - " + roomNumber + "has been set to " + roomType + " successfully.");
                        } else if (roomTypeIndicator == 2) {
                            roomType = RoomType.DOUBLE;
                            roomTypeRunning = false;
                            roomTypeString = "DOUBLE";
                            System.out.println("The Room Type for Room Number - " + roomNumber + "has been set to " + roomType + " successfully.");
                        } else if (roomTypeString == "quit") {
                            roomTypeRunning = false;
                            keepRunning = false;
                        } else {
                            System.out.println("Please enter a number 1, or 2. Type 'quit' to cancel adding Room Number - " + roomNumber + ".");
                        }
                    }
                    System.out.println("What Price do you want to set for Room Number - " + roomNumber + " (" + roomTypeString + ")?");
                    System.out.println("quit - Cancel creating room number - " + roomNumber + ".");
                    String roomPrice = "";
                    double roomPriceDouble = 0.0;
                    boolean roomPriceRunning = true;
                    while (roomPriceRunning) {
                        roomPrice = scanner.nextLine();
                        if (isDoubleString(roomPrice)) {
                            roomPriceDouble = Double.parseDouble(roomPrice);
                            roomPriceRunning = false;
                        } else if (roomPrice == "quit") {
                            roomPriceRunning = false;
                            keepRunning = false;
                        } else {
                            System.out.println("Please enter a price for room number - " + roomNumber + " ( " + roomType + " ). Type 'quit' to cancel adding Room Number - \" + roomNumber + .");
                        }
                    }
                    IRoom newRoom = new Room(roomNumber, roomPriceDouble, roomType);
                    List<IRoom> newRoomList = new ArrayList<>();
                    newRoomList.add(newRoom);
                    AdminResource.addRoom(newRoomList);
                    System.out.println("New Room - " + newRoom.toString() + "has been created.");
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

    public static boolean isIntegerString(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDoubleString(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
