package com.lee.redis.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import com.lee.redis.api.UserController;
import com.lee.redis.domain.Journey;
import com.lee.redis.domain.User;
import com.lee.redis.repository.UserRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private UserController userController;

    @Before
    public void setUser() {
        userService.createUsers();
    }

    @Test
    public void findJourneyByUserIdExistsTest() {
        Long userId = 1L;
        String cityJourney = "New York ";
        List<Journey> expected = userService.findJourneysByUserId(userId);

        Assert.assertNotNull(expected);
        assertTrue(expected.size() > 0);
        assertEquals(cityJourney.concat(userId.toString()), expected.get(0).getCity());
    }

    @Test
    public void findJourneysByIdExistsAndLessThan7MsTest(){
        Random random = new Random();
        Integer userId = random.nextInt(10000);
        Integer journeyPosition = random.nextInt(100);

        List<Journey> journeysByUser = userService.findJourneysByUserId(userId.longValue());
        Journey journey = journeysByUser.get(journeyPosition);
        //System.out.println("journeyId: " + journey.getJourneyId());
        String journeyId =  journey.getJourneyId();

        LocalDateTime timeBefore = LocalDateTime.now();
        Journey expected = journeyService.findByJourneyId(journey.getJourneyId(), userId.longValue());

        LocalDateTime timeAfter =  LocalDateTime.now();
        long timeMillis = ChronoUnit.MILLIS.between(timeBefore, timeAfter);
        System.out.println("Time millis: " + timeMillis);

        Assert.assertNotNull(expected);
        assertTrue(timeMillis <= 7);
    }

    @Test
    public void findJourneysByUserIdExistsAndLessThan5MsTest(){
        Random random = new Random();
        Integer userId = random.nextInt(10000);
        //System.out.println("userId: " + userId);

        LocalDateTime timeBefore = LocalDateTime.now();
        List<Journey> expected = userService.findJourneysByUserId(userId.longValue());
        LocalDateTime timeAfter =  LocalDateTime.now();

        long timeMillis = ChronoUnit.MILLIS.between(timeBefore, timeAfter);
        System.out.println("Time millis: " + timeMillis);

        Assert.assertNotNull(expected);
        assertTrue(expected.size() > 0);
        assertTrue(timeMillis <= 5);
    }

    @Test
    public void findJourneysByUserIdNotExistsAndLessThan3MsTest(){
        LocalDateTime timeBefore = LocalDateTime.now();
        List<Journey> expected = userService.findJourneysByUserId(999999999999999L);
        LocalDateTime timeAfter =  LocalDateTime.now();
        long timeMillis = ChronoUnit.MILLIS.between(timeBefore, timeAfter);
        System.out.println("Time millis: " + timeMillis);

        Assert.assertNotNull(expected);
        assertTrue(expected.size() == 0);
        assertTrue(timeMillis <= 3);
    }

    @Test
    public void findJourneyByIdDoesNotExist(){
        LocalDateTime timeBefore = LocalDateTime.now();
        Journey expected = journeyService.findByJourneyId("111", 11L);
        LocalDateTime timeAfter =  LocalDateTime.now();
        long timeMillis = ChronoUnit.MILLIS.between(timeBefore, timeAfter);
        System.out.println("Time millis: " + timeMillis);
        assertTrue(timeMillis <= 2);

    }
}
