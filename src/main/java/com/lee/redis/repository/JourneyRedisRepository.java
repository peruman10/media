package com.lee.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.lee.redis.domain.Journey;

public interface JourneyRedisRepository extends CrudRepository<Journey, String> {
}
