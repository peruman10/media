package com.lee.redis.repository;

import com.lee.redis.domain.Journey;
import com.lee.redis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class UserRepository {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public void save(User user) {
        redisTemplate.opsForValue().set(user.getId().toString(), user);
    }

    public User findById(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<Journey> findJourneysByUserId(Long key) {
        List<Journey> journeyList = new ArrayList<>();

        Set<String> keys = redisTemplate.keys(key.toString());
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            User user = findById(it.next());
            journeyList = user.getJourneys();
        }

        return journeyList;
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            userList.add(findById(it.next()));
        }

        return userList;
    }

}
