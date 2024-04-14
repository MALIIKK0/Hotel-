package com.HotelMalik.reserv2.service.impl;

import com.HotelMalik.reserv2.Exception.ResourceNotFoundException;
import com.HotelMalik.reserv2.entities.Reservation;
import com.HotelMalik.reserv2.entities.Room;
import com.HotelMalik.reserv2.repositories.ReservationRepository;
import com.HotelMalik.reserv2.repositories.RoomRepository;
import com.HotelMalik.reserv2.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, int capacity) {
        // Retrieve all rooms from the database
        List<Room> allRooms = roomRepository.findAll();

        // Initialize a list to store available rooms
        List<Room> availableRooms = new ArrayList<>();

        // Iterate over each room to check availability
        for (Room room : allRooms) {
            // Check if the room's capacity matches the required capacity
            if (room.getCapacity() >= capacity) {
                // Check if the room is available for the specified dates
                if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                    availableRooms.add(room);
                }
            }
        }

        return availableRooms;
    }

    // Method to check if the room is available for the specified dates
    private boolean isRoomAvailable(Room room, Date checkInDate, Date checkOutDate) {
        // Retrieve overlapping reservations for the room and specified dates
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(room, checkInDate, checkOutDate);

        // If there are no overlapping reservations, the room is available
        return overlappingReservations.isEmpty();
    }
    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        Room room = getRoomById(id);
        room.setPrix(updatedRoom.getPrix());
        room.setCapacity(updatedRoom.getCapacity());
        room.setDescription(updatedRoom.getDescription());
        room.setIsavailable(updatedRoom.isIsavailable());
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }
    @Override
    public Room updateRoomAvailability(Long id, boolean isAvailable) {
      {
            Room room = getRoomById(id);

            room.setIsavailable(isAvailable);
            return roomRepository.save(room);
        }
    }

}
