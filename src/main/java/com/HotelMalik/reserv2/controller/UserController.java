package com.HotelMalik.reserv2.controller;

import com.HotelMalik.reserv2.entities.User;
import com.HotelMalik.reserv2.repositories.UserRepository;
import com.HotelMalik.reserv2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    // Create user
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.CreateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    //Get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.GetAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.GetUserById(id);
        return  ResponseEntity.ok(user);

    }
    // Update user
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    User user = userService.updateUser(id,userDetails);
            return ResponseEntity.ok(user);

        }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
    userService.deleteEmploye(id);
    return  ResponseEntity.ok("user deleted");
    }
    }



