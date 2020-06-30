package ru.spring.artproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.artproject.domain.History;
import ru.spring.artproject.repository.HistoryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ConverterService {
    @Autowired
    HistoryRepository historyRepository;

    public List<History> findAllHistory() {
        return StreamSupport.stream(historyRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public void saveHistory(History history) {
        historyRepository.save(history);
    }
}
