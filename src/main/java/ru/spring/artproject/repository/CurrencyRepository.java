package ru.spring.artproject.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.spring.artproject.domain.Currency;

public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Integer> {



}
