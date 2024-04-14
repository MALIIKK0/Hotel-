package com.HotelMalik.reserv2.controller;

import com.HotelMalik.reserv2.entities.Room;
import com.HotelMalik.reserv2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Create room
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room savedRoom = roomService.addRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    // Get all rooms
    @GetMapping
    public List<Room> getRooms() {
        return roomService.getAllRooms();
    }
    // Get available  rooms
    @GetMapping("/av")
    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, int capacity) {
        return
                roomService.getAvailableRooms(checkInDate,checkOutDate,capacity);
    }

    // Get room by id
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    // Update room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Room updatedRoom = roomService.updateRoom(id, roomDetails);
        return ResponseEntity.ok(updatedRoom);
    }

    // Delete room
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room with id=" + id + " deleted");
    }
    @PutMapping("/{id}/availability")
    public ResponseEntity<String> updateRoomAvailability(
            @PathVariable Long id,
            @RequestParam boolean isAvailable) {
        roomService.updateRoomAvailability(id, isAvailable);
        return ResponseEntity.ok("Room availability updated successfully");
    }
}
