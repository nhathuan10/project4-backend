package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.MessageDto;
import com.springboot.project4.project4backend.security.JwtAuthenticationFilter;
import com.springboot.project4.project4backend.security.JwtTokenProvider;
import com.springboot.project4.project4backend.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class MessageController {
    private MessageService messageService;
    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/messages")
    public ResponseEntity<MessageDto> postMessage(HttpServletRequest request, @RequestBody MessageDto messageDto){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(messageService.postMessage(messageDto, userEmail), HttpStatus.CREATED);
    }

    @GetMapping("/messages/findByUser")
    public ResponseEntity<List<MessageDto>> getMessagesByUserEmail(HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(messageService.findMessagesByUserEmail(userEmail), HttpStatus.OK);
    }
}
