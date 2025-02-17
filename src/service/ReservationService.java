package service;

import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    public static final Map<String, IRoom> roomsMap = new HashMap<>();
    private final static Map<Date, Set<IRoom>> reservedRoomsDateMap = new HashMap<>();
    private final static Map<String, Set<Reservation>> reservedRoomsCustomerMap = new HashMap<>();
    public static void addRoom(IRoom room) {
        roomsMap.put(room.getRoomNumber(), room);
    }
    public static IRoom getARoom(String roomId) {
        return roomsMap.get(roomId);
    }
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Set<IRoom> availableRooms = findRooms(checkInDate, checkOutDate);

        if (availableRooms.contains(room)) {
            Calendar calendar = Calendar.getInstance();
            for(calendar.setTime(checkInDate); !calendar.getTime().after(checkOutDate); calendar.add(Calendar.DAY_OF_MONTH, 1)) {
                Set<IRoom> occupiedRooms = reservedRoomsDateMap.get(calendar.getTime());
                FreeRoom newOccupiedRoom = new FreeRoom(room.getRoomNumber(), room.getRoomType());
                occupiedRooms.add(newOccupiedRoom);
                reservedRoomsDateMap.put(calendar.getTime(), occupiedRooms);
            }
            Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
            Set<Reservation> customerReservations = reservedRoomsCustomerMap.get(customer.getEmail());
            customerReservations.add(newReservation);
            reservedRoomsCustomerMap.put(customer.getEmail(), customerReservations);
            return newReservation;
        } else {
            return null;
        }
    }
    public static Set<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        //get all rooms
        Set<String> allRoomsNumber = roomsMap.keySet();
        Set<IRoom> allRooms = new HashSet<>();
        for(String roomNumber: allRoomsNumber) {
            allRooms.add(getARoom(roomNumber));
        }

        //get reserved rooms between given check-in and check-out dates
        Calendar calender = Calendar.getInstance();
        Set<IRoom> reservedRoomsBetweenCheckInCheckOut = new HashSet<>();
        for (calender.setTime(checkInDate); !calender.getTime().after(checkOutDate); calender.add(Calendar.DAY_OF_MONTH, 1)) {
            if (reservedRoomsDateMap.containsKey(calender.getTime())) {
                reservedRoomsBetweenCheckInCheckOut.addAll(reservedRoomsDateMap.get(calender.getTime()));
            }
        }

        //get available rooms between check-in and check-out dates
        Set<IRoom> availableRooms = new HashSet<>(allRooms);
        availableRooms.removeAll(reservedRoomsBetweenCheckInCheckOut);
        return availableRooms;
    }
    public static Set<Reservation> getCustomerReservation(Customer customer){
        return reservedRoomsCustomerMap.get(customer.getEmail());
    }
    public static void printAllReservation(){
        System.out.println(reservedRoomsCustomerMap.values());
    }
}
