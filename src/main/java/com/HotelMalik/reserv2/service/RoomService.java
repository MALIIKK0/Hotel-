package com.HotelMalik.reserv2.service;

import com.HotelMalik.reserv2.entities.Room;
import com.HotelMalik.reserv2.entities.User;

import java.util.Date;
import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    List<Room> getAllRooms();
    Room getRoomById(Long id);

    Room updateRoom(Long id,Room UpdatedRoom);

    void deleteRoom(Long id);
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, int capacity);

    Room updateRoomAvailability(Long roomId, boolean isAvailable);
}
