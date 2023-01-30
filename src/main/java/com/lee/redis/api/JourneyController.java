package com.lee.redis.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lee.redis.domain.Journey;
import com.lee.redis.service.JourneyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/journey")
@Api(value = "/api", description = "journey", tags = {"Journey"})
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "Wrong Type Parameter"),
    @ApiResponse(code = 404, message = "Does not exists journey"),
    @ApiResponse(code = 500, message = "Server Error")})
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @ApiOperation(value = "Getting journey by ID", notes = "This API is to get journey by ID")
    @GetMapping(path = "/{journey_id}")
    public ResponseEntity<Journey> getJourneysById(@RequestHeader(required = true, value = "api-user-id") long apiUserId, @PathVariable("journey_id") String journeyId) {
        ResponseEntity<Journey> responseEntity;
        String apiUser = String.valueOf(apiUserId);

        if (!apiUser.matches("[0-9]+") || apiUserId <= 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (journeyId == null || journeyId.length() <= 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Journey journey = journeyService.findByJourneyId(journeyId, apiUserId);

            if (journey != null) {
                responseEntity = new ResponseEntity<>(journey, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        }

        return responseEntity;
    }
}
