package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.entity.User;

public interface UserService {
    User findUserRoles(String usernameOrEmail, String userNameOrEmail);
}
