package model;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room (String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return "Room Number:" + roomNumber + ", Price:$" + price + ", Room Type:" + enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return this.price != null && this.price != 0.0;
    }
}
