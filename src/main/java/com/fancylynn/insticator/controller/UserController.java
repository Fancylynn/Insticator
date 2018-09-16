package com.fancylynn.insticator.controller;

import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.dto.ResponseDto;
import com.fancylynn.insticator.model.QuestionResponse;
import com.fancylynn.insticator.model.User;
import com.fancylynn.insticator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
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

    // HTTP.POST for creating new user if the current ip address was not in the existing list
    @RequestMapping(method = RequestMethod.POST, path = "/newUser")
    public ResponseEntity<User> createNewUser (
            @RequestParam String ipAddress
    ) throws EntityExistsException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User newUser = userService.createNewUser(ipAddress);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

    }

}
