package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.entity.User;
import com.springboot.project4.project4backend.repository.UserRepository;
import com.springboot.project4.project4backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User findUserRoles(String usernameOrEmail, String userNameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
    }
}
