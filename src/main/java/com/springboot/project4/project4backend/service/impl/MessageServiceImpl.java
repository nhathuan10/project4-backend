package com.springboot.project4.project4backend.service.impl;

import com.springboot.project4.project4backend.dto.MessageDto;
import com.springboot.project4.project4backend.dto.MessageResponse;
import com.springboot.project4.project4backend.entity.Message;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.MessageMapper;
import com.springboot.project4.project4backend.repository.MessageRepository;
import com.springboot.project4.project4backend.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    @Override
    public MessageDto postMessage(MessageDto messageDto, String userEmail) {
        Message message = MessageMapper.mapToEntity(messageDto);
        message.setUserEmail(userEmail);
        Message savedMessage = messageRepository.save(message);
        return MessageMapper.mapToDto(savedMessage);
    }

    @Override
    public List<MessageDto> findMessagesByUserEmail(String userEmail) {
        List<Message> messages = messageRepository.findByUserEmail(userEmail);
        return messages.stream().map(MessageMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> findMessagesByClosed(boolean closed) {
        List<Message> messages = messageRepository.findByClosed(closed);
        return messages.stream().map(MessageMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public MessageDto responseMessage(long id, MessageDto messageDto, String userEmail) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message", "id", id));
        message.setAdminEmail(userEmail);
        message.setResponse(messageDto.getResponse());
        message.setClosed(true);
        Message savedMessage = messageRepository.save(message);
        return MessageMapper.mapToDto(savedMessage);
    }

    @Override
    public MessageResponse getAllMessages(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Message> messages = messageRepository.findAll(pageable);
        List<Message> messageList = messages.getContent();
        List<MessageDto> content = messageList.stream().map(MessageMapper::mapToDto).collect(Collectors.toList());
        return new MessageResponse(content, messages.getNumber(), messages.getSize(), messages.getTotalElements(), messages.getTotalPages(), messages.isLast());
    }

    @Override
    public void deleteMessage(long id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message", "id", id));
        messageRepository.deleteById(id);
    }
}
