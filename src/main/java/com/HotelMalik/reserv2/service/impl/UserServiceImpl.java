package com.HotelMalik.reserv2.service.impl;

import com.HotelMalik.reserv2.Exception.ResourceNotFoundException;
import com.HotelMalik.reserv2.entities.User;
import com.HotelMalik.reserv2.repositories.UserRepository;
import com.HotelMalik.reserv2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User CreateUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User GetUserById(Long id) {
return
       userRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("user is not exist with the given id"+id));

    }

    @Override
    public User updateUser(Long id , User UpdatedUser ) {
       User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with  id"+id));
       user.setUsername(UpdatedUser.getUsername());
       user.setEmail(UpdatedUser.getEmail());
       user.setPassword(UpdatedUser.getPassword());
        return userRepository.save(user) ;
    }

    @Override
    public void deleteEmploye(Long id) {
         User user = userRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("user not found with the given id"+id));
         userRepository.deleteById(id);
    }
}
