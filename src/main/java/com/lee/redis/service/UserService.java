package com.lee.redis.service;

import com.lee.redis.domain.Journey;
import com.lee.redis.domain.User;
import com.lee.redis.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${users}")
    private Long numberUsers;

    @Value("${journeys}")
    private Long numberJourneys;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId().toString());
    }

    public List<Journey> findJourneysByUserId(Long userId) {
        return userRepository.findJourneysByUserId(userId);
    }

    public void createUsers() {
        List<Journey> listJourneys;
        Journey journey;
        User user;

        for (int i = 1; i <= numberUsers; i++) {
            listJourneys = new ArrayList<>();

            for (int j = 1; j <= numberJourneys; j++) {
                journey = new Journey(UUID.randomUUID().toString(), "New York " + j, "United States " + j);
                listJourneys.add(journey);
            }

            user = new User(Long.valueOf(i), "User " + i, "Test " + i, "35", listJourneys);
            userRepository.save(user);
        }

        System.out.println(userRepository.findAll());
    }
}
