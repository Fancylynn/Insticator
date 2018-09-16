package com.fancylynn.insticator.controller;

import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lynn on 2018/9/16.
 */
@RestController
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)
public class UserController {
    // Set the rolling period of how many questions should be return every time
    private Integer rollingPeriod = 2;

    @Autowired
    private UserService userService;

    // HTTP.GET for returning the questions for a specific user from a starting point
    @RequestMapping(method = RequestMethod.GET, path = "/userQuestions")
    public @ResponseBody
    ResponseEntity<List<QuestionDto>> getUserQuestionList(
            @RequestParam String ipAddress
    ) {
        return new ResponseEntity<List<QuestionDto>>(
                userService.getUserQuestionList(rollingPeriod, ipAddress), HttpStatus.OK);
    }
}
