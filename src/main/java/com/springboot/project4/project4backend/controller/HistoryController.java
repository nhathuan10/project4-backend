package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.HistoryDto;
import com.springboot.project4.project4backend.security.JwtAuthenticationFilter;
import com.springboot.project4.project4backend.security.JwtTokenProvider;
import com.springboot.project4.project4backend.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class HistoryController {
    private HistoryService historyService;
    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @GetMapping("/histories")
    public ResponseEntity<List<HistoryDto>> findAllHistories(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(historyService.findAllHistories(userEmail), HttpStatus.OK);
    }
}
