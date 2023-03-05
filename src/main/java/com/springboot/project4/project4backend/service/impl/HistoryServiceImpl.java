package com.springboot.project4.project4backend.service.impl;
import com.springboot.project4.project4backend.dto.HistoryDto;
import com.springboot.project4.project4backend.entity.History;
import com.springboot.project4.project4backend.mapper.HistoryMapper;
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

    @Override
    public List<HistoryDto> findAllHistories(String userEmail) {
        List<History> histories = historyRepository.findByUserEmail(userEmail);
        return histories.stream().map(HistoryMapper::mapToDto).collect(Collectors.toList());
    }
}
