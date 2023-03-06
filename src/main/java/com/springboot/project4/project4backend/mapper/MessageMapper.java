package com.springboot.project4.project4backend.mapper;

import com.springboot.project4.project4backend.dto.MessageDto;
import com.springboot.project4.project4backend.entity.Message;

public class MessageMapper {
    public static MessageDto mapToDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .userEmail(message.getUserEmail())
                .title(message.getTitle())
                .question(message.getQuestion())
                .adminEmail(message.getAdminEmail())
                .response(message.getResponse())
                .closed(message.isClosed())
                .build();
    }

    public static Message mapToEntity(MessageDto messageDto){
        return Message.builder()
                .id(messageDto.getId())
                .userEmail(messageDto.getUserEmail())
                .title(messageDto.getTitle())
                .question(messageDto.getQuestion())
                .adminEmail(messageDto.getAdminEmail())
                .response(messageDto.getResponse())
                .closed(messageDto.isClosed())
                .build();
    }
}
