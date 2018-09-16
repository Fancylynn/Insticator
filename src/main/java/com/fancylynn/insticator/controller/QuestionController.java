package com.fancylynn.insticator.controller;

import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.service.QuestionService;
import com.fancylynn.insticator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lynn on 2018/9/15.
 */

@RestController
@RequestMapping(path="/question") // This means URL's start with /user (after Application path)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // HTTP.GET for all the existing questions in the database
    @RequestMapping(method = RequestMethod.GET, path = "/questionList")
    public @ResponseBody ResponseEntity<List<QuestionDto>> getQuestionList() {
        return new ResponseEntity<List<QuestionDto>>(questionService.getAllQuestions(), HttpStatus.OK);
    }
}
