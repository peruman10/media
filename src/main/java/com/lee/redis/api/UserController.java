package com.lee.redis.api;

import com.lee.redis.domain.Journey;
import com.lee.redis.domain.User;
import com.lee.redis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/user")
@Api(value = "/api", description = "user", tags = {"User"})
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "Wrong Type Parameter"),
    @ApiResponse(code = 404, message = "Does not exists user"),
    @ApiResponse(code = 500, message = "Server Error")})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Creating user data\"",
            notes = "This API loads user data")
    @GetMapping(path = "/createData")
    public void createData() {
        userService.createUsers();
    }

    @ApiOperation(value = "Getting all journeys by User Id", notes = "This API is to get all journeys by User Id")
    @GetMapping(path = "/{user_id}/journeys")
    public ResponseEntity<List<Journey>> getJourneysByUserId(@RequestHeader(required = true, value = "api-user-id") Long apiUserId, @PathVariable("user_id") Long userId) {
        ResponseEntity<List<Journey>> responseEntity;
        String apiUser = String.valueOf(apiUserId);

        if (!apiUser.matches("[0-9]+") || apiUserId <= 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (userId == null || userId <= 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<Journey> listJourneys = userService.findJourneysByUserId(userId);
            if (listJourneys != null && !listJourneys.isEmpty()) {
                responseEntity = new ResponseEntity<>(listJourneys, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
            }
        }

        return responseEntity;
    }
}
