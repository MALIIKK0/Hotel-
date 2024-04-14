package com.HotelMalik.reserv2.entities;

import com.HotelMalik.reserv2.enums.Statue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Reservation {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resid;
    @Temporal(TemporalType.DATE)
    private Date CheckInDate;
    @Temporal(TemporalType.DATE)
    private Date CheckOutDate;
    private float totalPrice;
    @Enumerated(EnumType.STRING)
    private Statue statue;
    @ManyToOne
    private User user;
    @ManyToOne

    private Room room;




}
