package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.LoginDto;
import com.springboot.project4.project4backend.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
