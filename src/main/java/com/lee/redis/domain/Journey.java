package com.lee.redis.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@RedisHash("journey")
@AllArgsConstructor
@NoArgsConstructor
public class Journey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Indexed
    @Id
    private String journeyId;
    private String city;
    private String country;
}
