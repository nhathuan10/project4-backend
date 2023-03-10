package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.HistoryDto;
import com.springboot.project4.project4backend.security.JwtAuthenticationFilter;
import com.springboot.project4.project4backend.security.JwtTokenProvider;
import com.springboot.project4.project4backend.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/histories/find-by-user")
    public ResponseEntity<List<HistoryDto>> findAllHistoriesByUser(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(historyService.findAllHistoriesByUser(userEmail), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/histories/{historyId}/verifyBookReturned")
    public ResponseEntity<String> verifyBookReturned(@PathVariable("historyId") long historyId){
        historyService.verifyBookReturned(historyId);
        return new ResponseEntity<>("Book returned has verified successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/histories")
    public ResponseEntity<List<HistoryDto>> findAllHistories(){
        return new ResponseEntity<>(historyService.findAllHistories(), HttpStatus.OK);
    }
}
