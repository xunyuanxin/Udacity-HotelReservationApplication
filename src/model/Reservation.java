package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Customer:" + customer +
                ", Room Number:" + room.getRoomNumber() +
                ", Room Price:" + room.getRoomPrice() +
                ", Room Type:" + room.getRoomType() +
                ", Check In:" + checkInDate +
                ", Check Out:" + checkOutDate;
    }
}
