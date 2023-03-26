package com.springboot.project4.project4backend.service;

import com.springboot.project4.project4backend.dto.HistoryDto;
import java.util.List;

public interface HistoryService {
    List<HistoryDto> findAllHistoriesByUser(String userEmail);
    void verifyBookReturned(long historyId);
    List<HistoryDto> findAllHistories();
    void deleteHistory(long id);
}
