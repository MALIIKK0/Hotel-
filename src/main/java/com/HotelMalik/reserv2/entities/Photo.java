package com.HotelMalik.reserv2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Photo {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Photoid;
    private String url;
    private String description;
    @ManyToOne
    @JsonIgnore
    private Room room;

}
