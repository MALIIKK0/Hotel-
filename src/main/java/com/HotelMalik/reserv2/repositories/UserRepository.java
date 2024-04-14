package com.HotelMalik.reserv2.repositories;

import com.HotelMalik.reserv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
