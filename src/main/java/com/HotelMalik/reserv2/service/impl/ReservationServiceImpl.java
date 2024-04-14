package com.HotelMalik.reserv2.service.impl;

import com.HotelMalik.reserv2.Exception.ResourceNotFoundException;
import com.HotelMalik.reserv2.dto.ReservationDto;
import com.HotelMalik.reserv2.dto.RoomDto;
import com.HotelMalik.reserv2.dto.UserDTo;
import com.HotelMalik.reserv2.entities.Reservation;
import com.HotelMalik.reserv2.entities.Room;
import com.HotelMalik.reserv2.enums.Statue;
import com.HotelMalik.reserv2.repositories.ReservationRepository;
import com.HotelMalik.reserv2.service.ReservationService;
import com.HotelMalik.reserv2.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {


    private final  ReservationRepository reservationRepository;

    @Override
    public ReservationDto registerReservation(ReservationDto reservationDto) {
        // Check if the room is available for the specified dates
        Reservation reservation = ReservationDto.toEntity(reservationDto);
        if (!isRoomAvailable(reservation.getRoom(), reservation.getCheckInDate(), reservation.getCheckOutDate())) {
            throw new IllegalArgumentException("The room is not available for the specified dates");
        }

        // Calculate the total price of the reservation
        float totalPrice = calculateTotalPrice(reservation.getRoom(), reservation.getCheckInDate(), reservation.getCheckOutDate());

        // Set the total price in the reservation object
        reservation.setTotalPrice(totalPrice);

        // Save the reservation to the database
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationDto.ToDto(savedReservation);
    }

    // Method to check if the room is available for the specified dates
    private boolean isRoomAvailable(Room room, Date checkInDate, Date checkOutDate) {

        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(room, checkInDate, checkOutDate);

        return overlappingReservations.isEmpty();
    }

    // Method to calculate the total price of the reservation
    private float calculateTotalPrice(Room room, Date checkInDate, Date checkOutDate) {
        long durationInMillis = checkOutDate.getTime() - checkInDate.getTime();
        int numberOfNights = (int) (durationInMillis / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
        float pricePerNight = room.getPrix(); // Assuming "prix" is the price per night for the room
        return numberOfNights * pricePerNight;
    }
    /*@Override
    public List<Reservation> getUserReservations(Long userId) {
        // Retrieve reservations for the specified user ID from the repository
        return reservationRepository.findByUserId(userId);
    }*/
    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationDto::ToDto)
                .collect(Collectors.toList());
    }
/*@Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationDto::toDto)
                .collect(Collectors.toList());
    }*/
@Override
public ReservationDto getReservationById(Long id) {
    Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));
    return ReservationDto.ToDto(reservation);
}

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

        // Update fields of existing reservation with values from the provided DTO
        existingReservation.setCheckInDate(reservationDto.getCheckInDate());
        existingReservation.setCheckOutDate(reservationDto.getCheckOutDate());
        existingReservation.setStatue(reservationDto.getStatue());
        existingReservation.setUser(UserDTo.toEntity(reservationDto.getUser()));
        existingReservation.setRoom(RoomDto.toEntity(reservationDto.getRoom()));

        // Save the updated reservation
        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return ReservationDto.ToDto(updatedReservation);
    }
    @Override

    public ReservationDto updateReservationStatus(Long reservationId, Statue newStatus) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + reservationId));

        reservation.setStatue(newStatus);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return ReservationDto.ToDto(updatedReservation);
    }

}

