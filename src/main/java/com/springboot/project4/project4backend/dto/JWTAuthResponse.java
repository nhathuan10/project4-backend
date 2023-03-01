package com.springboot.project4.project4backend.dto;

import com.springboot.project4.project4backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
    private String accessToken;
    private String userEmail;
    private Set<Role> roles;
    private String tokenType = "Bearer";
}
