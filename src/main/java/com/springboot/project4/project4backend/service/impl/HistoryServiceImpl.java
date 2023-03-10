package com.springboot.project4.project4backend.service.impl;
import com.springboot.project4.project4backend.dto.HistoryDto;
import com.springboot.project4.project4backend.entity.Book;
import com.springboot.project4.project4backend.entity.History;
import com.springboot.project4.project4backend.exception.ResourceNotFoundException;
import com.springboot.project4.project4backend.mapper.HistoryMapper;
import com.springboot.project4.project4backend.repository.BookRepository;
import com.springboot.project4.project4backend.repository.CheckoutRepository;
import com.springboot.project4.project4backend.repository.HistoryRepository;
import com.springboot.project4.project4backend.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepository historyRepository;
    private CheckoutRepository checkoutRepository;
    private BookRepository bookRepository;

    @Override
    public List<HistoryDto> findAllHistoriesByUser(String userEmail) {
        List<History> histories = historyRepository.findByUserEmail(userEmail);
        return histories.stream().map(HistoryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void verifyBookReturned(long historyId) {
        History history = historyRepository.findById(historyId).orElseThrow(() -> new ResourceNotFoundException("History", "id", historyId));
        historyRepository.save(history);
        Book book = bookRepository.findById(history.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book", "id", history.getBookId()));
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);
        checkoutRepository.deleteById(history.getCheckout().getId());
    }

    @Override
    public List<HistoryDto> findAllHistories() {
        List<History> histories = historyRepository.findAll();
        return histories.stream().map(HistoryMapper::mapToDto).collect(Collectors.toList());
    }
}
