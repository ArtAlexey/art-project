package ru.spring.artproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.spring.artproject.domain.History;

import java.util.Date;
import java.util.Set;

public interface HistoryRepository extends PagingAndSortingRepository<History, Long> {

    @Query(value = "SELECT * FROM history INNER JOIN users_history ON history.id = users_history.histories_id WHERE user_id = ?1 AND history.namein = ?2", nativeQuery = true)
    Set<History> getHistoriesByNameIn(Long userId, String nameIn);

    @Query(value = "SELECT * FROM history INNER JOIN users_history ON history.id = users_history.histories_id WHERE user_id = ?1 AND history.namein = ?2 AND history.nameout = ?3", nativeQuery = true)
    Set<History> getHistoriesByNameInNAndNameOut(Long userId, String nameIn, String nameOut);

    @Query(value = "SELECT * FROM history INNER JOIN users_history ON history.id = users_history.histories_id WHERE user_id = ?1 AND history.namein = ?2 AND history.nameout = ?3 AND history.datein = ?4", nativeQuery = true)
    Set<History> getHistoriesByNameInNAndNameOutAndDateIn(Long userId, String nameIn, String nameOut, Date dateIn);

}
