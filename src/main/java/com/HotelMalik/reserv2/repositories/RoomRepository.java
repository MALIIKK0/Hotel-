package com.HotelMalik.reserv2.repositories;

import com.HotelMalik.reserv2.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
