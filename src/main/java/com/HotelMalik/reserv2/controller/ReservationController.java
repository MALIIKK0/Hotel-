
package com.HotelMalik.reserv2.controller;

import com.HotelMalik.reserv2.dto.ReservationDto;
import com.HotelMalik.reserv2.enums.Statue;
import com.HotelMalik.reserv2.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> registerReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto savedReservation = reservationService.registerReservation(reservationDto);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservationDto = reservationService.getReservationById(id);
        if (reservationDto != null) {
            return ResponseEntity.ok(reservationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
        if (updatedReservation != null) {
            return ResponseEntity.ok(updatedReservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{reservationId}/status")
    public ResponseEntity<ReservationDto> updateReservationStatus(
            @PathVariable Long reservationId,
            @RequestParam Statue newStatus) {
        ReservationDto updatedReservation = reservationService.updateReservationStatus(reservationId, newStatus);
        return ResponseEntity.ok(updatedReservation);
    }
}
