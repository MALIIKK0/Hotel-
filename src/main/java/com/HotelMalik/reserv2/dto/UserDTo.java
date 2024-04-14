package com.HotelMalik.reserv2.dto;

import com.HotelMalik.reserv2.entities.User;
import com.HotelMalik.reserv2.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTo {
    private Long userid;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    public static UserDTo ToDto(User entity){
        return UserDTo
                .builder()
                .userid(entity.getUserid())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();

    }
    public static User toEntity(UserDTo dto) {
        User user = new User();
        user.setUserid(dto.getUserid());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }


}
