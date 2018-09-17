package com.fancylynn.insticator.controller;

import com.fancylynn.insticator.dto.CreateQuestionDto;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.dto.ResponseDto;
import com.fancylynn.insticator.service.QuestionService;
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
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody ResponseEntity<List<QuestionDto>> getQuestionList() {
        return new ResponseEntity<List<QuestionDto>>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    // HTTP.POST for saving the user question response
    @RequestMapping(method = RequestMethod.POST, path = "/response")
    public ResponseEntity<Void> saveQuestionResponse (
            @RequestBody ResponseDto responseDto
    ){
        questionService.saveQuestionResponse(responseDto);

        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    // HTTP.POST for creating a new question
    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<QuestionDto> createNewQuestion (
            @RequestBody CreateQuestionDto createQuestionDto
    ){
        QuestionDto createdQuestion = questionService.createNewQuestion(createQuestionDto);

        return new ResponseEntity<QuestionDto>(createdQuestion, HttpStatus.CREATED);

    }

    // HTTP.DELETE for deleting an existing question
    @RequestMapping(method = RequestMethod.DELETE, path = "/{questionId}")
    public ResponseEntity<Void> deleteQuestion (
            @PathVariable Long questionId
    ){

        questionService.deleteQuestion(questionId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
