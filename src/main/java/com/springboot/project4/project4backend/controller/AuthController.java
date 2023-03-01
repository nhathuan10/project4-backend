package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.JWTAuthResponse;
import com.springboot.project4.project4backend.dto.LoginDto;
import com.springboot.project4.project4backend.dto.RegisterDto;
import com.springboot.project4.project4backend.service.AuthService;
import com.springboot.project4.project4backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private UserService userService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setUserEmail(loginDto.getUsernameOrEmail());
        jwtAuthResponse.setRoles(userService.findUserRoles(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()).getRoles());
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
