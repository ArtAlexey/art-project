package ru.spring.artproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.artproject.domain.History;
import ru.spring.artproject.domain.User;
import ru.spring.artproject.service.HistoryService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HistoryController {
    @Autowired
    public HistoryService historyService;

    @GetMapping(path = "/history")
    public String history(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<History> histories = historyService.getAllHistory(user);
        Set<String> historiesNameIn = StreamSupport.stream(historyService.getAllHistory(user).spliterator(),false).map(History::getNameIn).collect(Collectors.toSet());
        Set<String> historiesNameOut = StreamSupport.stream(historyService.getAllHistory(user).spliterator(),false).map(History::getNameOut).collect(Collectors.toSet());
        model.addAttribute("historiesNameIn", historiesNameIn);
        model.addAttribute("historiesNameOut", historiesNameOut);
        model.addAttribute("histories", histories);
        model.addAttribute("name", user.getName());
        return "history";
    }


    @PostMapping(value = "/history/getHistories")
    public String getHistories (@RequestParam String currencyIn, @RequestParam String currencyOut, @RequestParam String dateIn, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Set<History> histories = new HashSet<>();
        if (currencyIn.equals("0") & currencyOut.equals("0") & dateIn.equals("")) {
            return "redirect:/history?confirmedError=true";
        } else if (!currencyIn.equals("0") & currencyOut.equals("0") & dateIn.equals("")) {
            histories = historyService.getHistoriesByNameIn(userId, currencyIn);
        } else if (!currencyIn.equals("0") & !currencyOut.equals("0") & dateIn.equals("")) {
            histories = historyService.getHistoriesByNameInNAndNameOut(userId, currencyIn, currencyOut);
        } else if (!currencyIn.equals("0") & !currencyOut.equals("0") & !dateIn.equals("")){
            try {
                Date dateDb = new SimpleDateFormat("yyyy-MM-dd").parse(dateIn);
                Timestamp timestamp1 = new java.sql.Timestamp(dateDb.getTime());
                histories = historyService.getHistoriesByNameInNAndNameOutAndDateIn(userId, currencyIn, currencyOut, timestamp1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("histories", histories);
        model.addAttribute("name", user.getName());
        return "gethistory";
    }
}
