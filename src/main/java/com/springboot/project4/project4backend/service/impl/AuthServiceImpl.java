package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.LoginDto;
import com.springboot.project4.project4backend.dto.RegisterDto;
import com.springboot.project4.project4backend.entity.Role;
import com.springboot.project4.project4backend.entity.User;
import com.springboot.project4.project4backend.exception.APIException;
import com.springboot.project4.project4backend.repository.RoleRepository;
import com.springboot.project4.project4backend.repository.UserRepository;
import com.springboot.project4.project4backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged-in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        // check if user or email exists in DB
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "User is already exists!");
        }
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!";
    }
}
