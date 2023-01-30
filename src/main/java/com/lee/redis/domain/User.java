package com.lee.redis.domain;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@RedisHash("user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String fitsName;

    private String lastName;

    private String age;

    @Indexed
    @Reference
    private List<Journey> journeys;

}
