package com.HotelMalik.reserv2.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentificationController {
    private final AuthentificationService authenticationService;

    @PostMapping("/register_guest")
    public ResponseEntity<AuthentificationResponse> register_guest(
            @RequestBody RegisterRequest request

    ) {

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(
            @RequestBody AuthentificationRequest request

    ){
        return ResponseEntity.ok( authenticationService.authenticate(request));
//
    }
}