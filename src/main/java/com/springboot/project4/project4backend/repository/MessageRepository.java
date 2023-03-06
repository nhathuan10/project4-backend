package com.springboot.project4.project4backend.repository;

import com.springboot.project4.project4backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUserEmail(String userEmail);
    List<Message> findByClosed(boolean closed);
}
