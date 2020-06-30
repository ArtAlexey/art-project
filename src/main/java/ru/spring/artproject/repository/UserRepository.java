package ru.spring.artproject.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.spring.artproject.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
