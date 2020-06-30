package ru.spring.artproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.spring.artproject.domain.History;
import ru.spring.artproject.domain.User;
import ru.spring.artproject.repository.HistoryRepository;
import ru.spring.artproject.repository.RoleRepository;
import ru.spring.artproject.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service

public class UserService implements UserDetailsService {

 //   @PersistenceContext
 //   private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userDb = userRepository.findById(userId);
        return userDb.orElse(new User());
    }

    public List<User> allUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }


    public boolean saveUser(User user) {
       User userDb = userRepository.findByUsername(user.getUsername());
       if(userDb != null) {
           return false;
       }
        userRepository.save(user);
        return true;
    }

    public void saveHistoryBuId(History history, Long userId){
        User userIn = userRepository.findById(userId).get();
        Set<History> UserHistories = userIn.getHistories();
        UserHistories.add(history);
        userIn.setHistories(UserHistories);
        userRepository.save(userIn);
    }


    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.deleteById(userId);
        historyRepository.deleteAll(user.getHistories());
    }

}
