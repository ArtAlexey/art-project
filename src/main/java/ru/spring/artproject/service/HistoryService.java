package ru.spring.artproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.spring.artproject.domain.Currency;
import ru.spring.artproject.domain.History;
import ru.spring.artproject.domain.User;
import ru.spring.artproject.repository.HistoryRepository;


import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    UserService userService;

    public Set<History> getHistoriesByNameIn(Long userId, String nameIn) {
        return historyRepository.getHistoriesByNameIn(userId, nameIn);
    }

    public Set<History> getHistoriesByNameInNAndNameOut(Long userId, String nameIn, String nameOut) {
        return  historyRepository.getHistoriesByNameInNAndNameOut(userId, nameIn, nameOut);
    }

    public Set<History> getHistoriesByNameInNAndNameOutAndDateIn(Long userId, String nameIn, String nameOut, Date dateIn) {
        return historyRepository.getHistoriesByNameInNAndNameOutAndDateIn(userId, nameIn, nameOut, dateIn);
    }

    public void saveHistory(Currency currency1, Currency currency2, Double valueIn, Double valueOut) {
       try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
            Date newDate = new Date();
            String sDate = formatDate.format(newDate);
            History history = new History();
            history.setCharCodeIn(currency1.getCharCode());
            history.setNameIn(currency1.getName());
            history.setValueIn(valueIn);
            history.setCharCodeOut(currency2.getCharCode());
            history.setNameOut(currency2.getName());
            history.setValueOut(valueOut);
            history.setDateIn(new SimpleDateFormat("dd.MM.yyyy").parse(sDate));
            History historyOut = historyRepository.save(history);
            userService.saveHistoryBuId(historyOut, user.getId());
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
    }

    public Set<History> getAllHistory(User user) {
        Set<History> histories = user.getHistories();
        return histories;
    }

    public void deleteHistory(User user){
        historyRepository.deleteAll(user.getHistories());
    }
}
