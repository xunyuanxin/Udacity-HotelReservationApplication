package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AdminResource {
    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms) {
        for(IRoom room: rooms) {
            ReservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return ReservationService.roomsMap.values();
    }

    public static Set<String> getAllRoomNumbers() { return ReservationService.roomsMap.keySet(); }

    public static Collection<Customer> getAllCustomers() {
        return CustomerService.customersMap.values();
    }

    public static void displayAllReservations() {
        ReservationService.printAllReservation();
    }
}
