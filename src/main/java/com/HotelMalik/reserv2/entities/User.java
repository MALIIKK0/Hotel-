package com.HotelMalik.reserv2.entities;

import com.HotelMalik.reserv2.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;

}
