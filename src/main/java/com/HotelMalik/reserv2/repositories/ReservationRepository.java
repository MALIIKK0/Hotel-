package com.HotelMalik.reserv2.repositories;

import com.HotelMalik.reserv2.entities.Reservation;
import com.HotelMalik.reserv2.entities.Room;
import com.HotelMalik.reserv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r WHERE r.room = :room " +
            "AND ((r.CheckInDate BETWEEN :checkInDate AND :checkOutDate) " +
            "OR (r.CheckOutDate BETWEEN :checkInDate AND :checkOutDate))")
    List<Reservation> findOverlappingReservations(Room room, Date checkInDate, Date checkOutDate);



}
