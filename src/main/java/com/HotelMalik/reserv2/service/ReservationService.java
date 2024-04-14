package com.HotelMalik.reserv2.service;

import com.HotelMalik.reserv2.dto.ReservationDto;
import com.HotelMalik.reserv2.entities.Reservation;
import com.HotelMalik.reserv2.enums.Statue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    public ReservationDto registerReservation(ReservationDto reservationDto);
    List<ReservationDto> getAllReservations();
    ReservationDto getReservationById(Long id);
    ReservationDto updateReservation(Long id, ReservationDto reservationDto);

    //List<Reservation> getUserReservations(Long userId);
    ReservationDto updateReservationStatus(Long reservationId, Statue newStatus);
}
