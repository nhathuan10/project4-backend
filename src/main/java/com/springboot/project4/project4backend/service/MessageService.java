package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto postMessage(MessageDto messageDto, String userEmail);
    List<MessageDto> findMessagesByUserEmail(String userEmail);
}
