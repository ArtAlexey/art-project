package ru.spring.artproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.spring.artproject.domain.Currency;
import ru.spring.artproject.service.CurrencyService;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/")
    public String home(Model model) {
        currencyService.getCurrencies();
        List<Currency> currencies = currencyService.findAllCurrency();
        model.addAttribute("currencies", currencies);
        return "home";
    }

}
