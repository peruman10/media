package com.lee.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.redis.domain.Journey;
import com.lee.redis.repository.JourneyRepository;

@Service
public class JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    public Journey findByJourneyId(String id, Long userId) {
        return journeyRepository.findByJourneyId(id, userId);
    }


}
