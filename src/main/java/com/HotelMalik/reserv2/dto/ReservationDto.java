package com.HotelMalik.reserv2.dto;

import com.HotelMalik.reserv2.entities.Reservation;
import com.HotelMalik.reserv2.enums.Statue;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDto {
    private Long ReservationId;
    @Temporal(TemporalType.DATE)
    private Date checkInDate;
    @Temporal(TemporalType.DATE)
    private Date checkOutDate;
    private RoomDto room;
    private UserDTo user;
    private Statue statue;
    public static  ReservationDto ToDto(Reservation entity){
        return ReservationDto
                .builder()
                .ReservationId(entity.getResid())
                .checkInDate(entity.getCheckInDate())
                .checkOutDate(entity.getCheckOutDate())
                .room(RoomDto.toDto(entity.getRoom()))
                .user(UserDTo.ToDto(entity.getUser()))
                .statue(entity.getStatue())
                .build();
    }


    public static Reservation toEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setResid(dto.getReservationId()); // Changed from reservationId to follow Java naming conventions
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setRoom(RoomDto.toEntity(dto.getRoom()));
        reservation.setUser(UserDTo.toEntity(dto.getUser()));
        reservation.setStatue(dto.getStatue());
        return reservation;
    }


}