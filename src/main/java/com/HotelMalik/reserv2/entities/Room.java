package com.HotelMalik.reserv2.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
@Builder
@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Room {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomid;
    private float prix;
    private String description;
    private int capacity;
    private boolean isavailable;
    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private Collection<Photo> photoCollection;
    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;



}
