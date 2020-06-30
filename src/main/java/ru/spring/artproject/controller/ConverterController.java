package ru.spring.artproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.artproject.domain.Currency;

import ru.spring.artproject.service.ConverterService;
import ru.spring.artproject.service.CurrencyService;
import ru.spring.artproject.service.HistoryService;
import ru.spring.artproject.service.UserService;


import java.util.List;

@Controller
public class ConverterController {

    @Autowired
    ConverterService converterService;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;

    @GetMapping(path = "/converter")
    public String converter(Model model) {
        List<Currency> currencies = currencyService.findAllCurrency();
        int numberOne = 0;
        int numberTwo = 0;
        Double value1 = 0.00;
        model.addAttribute("valueIn", value1);
        model.addAttribute("numberOne`", numberOne);
        model.addAttribute("numberTwo", numberTwo);
        model.addAttribute("currencies", currencies);
        return  "converter";
    }

    @PostMapping(path = "/converter/converted")
    public String converted(@RequestParam String currencyIn, @RequestParam String valueIn,
                            @RequestParam String currencyOut, Model model) {
        Integer currencyInInt = Integer.parseInt(currencyIn);
        Integer currencyInOut = Integer.parseInt(currencyOut);
        if(currencyInInt == 0 || currencyInOut == 0) {
            return "redirect:/converter?confirmedError=true";
        }
        List<Currency> currencies = currencyService.findAllCurrency();
        Currency currency1 = currencyService.getCurrencyById(currencyInInt);
        Currency currency2 = currencyService.getCurrencyById(currencyInOut);
        Double value1 = Double.valueOf(valueIn.replace(',','.'));
        Double result = (value1 * currency2.getNominal() * currency1.getValue()) / (currency2.getValue() * currency1.getNominal());
        int numberOne = currency1.getId();
        int numberTwo = currency2.getId();
        model.addAttribute("valueIn", value1);
        model.addAttribute("numberOne", numberOne);
        model.addAttribute("numberTwo", numberTwo);
        model.addAttribute("currencies", currencies);
        model.addAttribute("result", result);
        String typeUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().toLowerCase();
        if (typeUser.equals("anonymoususer")) {
            return "converter";
        } else {
            historyService.saveHistory(currency1, currency2, value1, result);
            return "converter";
        }
    }


}
