package ru.spring.artproject.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.spring.artproject.domain.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}
