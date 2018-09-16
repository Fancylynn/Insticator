package com.fancylynn.insticator.controller;

import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lynn on 2018/9/15.
 */

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET, path = "/questionList")
    public @ResponseBody ResponseEntity<List<QuestionDto>> getQuestionList( ) {
        return new ResponseEntity<List<QuestionDto>>(questionService.getAllQuestions(), HttpStatus.OK);
    }
}
