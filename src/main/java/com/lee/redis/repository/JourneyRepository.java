package com.lee.redis.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.lee.redis.domain.Journey;
import com.lee.redis.domain.User;

@Repository
public class JourneyRepository {

    @Autowired
    private RedisTemplate<String, Journey> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    public Journey findByJourneyId(String key, Long userId) {
        User user = userRepository.findById(userId.toString());
        List<Journey> listJourneys = user.getJourneys();
        Journey journey = null;

        for (int i = 0; i < listJourneys.size(); i++) {
            if (listJourneys.get(i).getJourneyId().equalsIgnoreCase(key)) {
                journey = listJourneys.get(i);
            }
        }

        return journey;
    }

}
