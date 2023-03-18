package com.springboot.project4.project4backend.controller;

import com.springboot.project4.project4backend.dto.MessageDto;
import com.springboot.project4.project4backend.dto.MessageResponse;
import com.springboot.project4.project4backend.security.JwtAuthenticationFilter;
import com.springboot.project4.project4backend.security.JwtTokenProvider;
import com.springboot.project4.project4backend.service.MessageService;
import com.springboot.project4.project4backend.utils.AppConstants;
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

    @GetMapping("/messages/findByClosed")
    public ResponseEntity<List<MessageDto>> getMessagesByClosed(@RequestParam(value = "closed", defaultValue = "false", required = false) boolean closed){
        return new ResponseEntity<>(messageService.findMessagesByClosed(closed), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/messages")
    public ResponseEntity<MessageResponse> getAllMessages(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(messageService.getAllMessages(pageNo, pageSize, sortBy, sortDir));
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<MessageDto> responseMessage(@PathVariable("messageId") long id, @RequestBody MessageDto messageDto, HttpServletRequest request){
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsernameFromToken(token);
        return new ResponseEntity<>(messageService.responseMessage(id, messageDto, userEmail), HttpStatus.OK);
    }
}
