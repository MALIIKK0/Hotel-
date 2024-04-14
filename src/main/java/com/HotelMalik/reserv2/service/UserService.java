package com.HotelMalik.reserv2.service;

import com.HotelMalik.reserv2.entities.User;

import java.util.List;

public interface UserService {
    User CreateUser(User user);
    List<User> GetAllUsers();
    User GetUserById(Long id);

    User updateUser(Long id,User UpdatedUser);

    void deleteEmploye(Long id);
}
