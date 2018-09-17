package com.fancylynn.insticator.service;

import com.fancylynn.insticator.dao.*;
import com.fancylynn.insticator.dto.CreateQuestionDto;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.dto.ResponseDto;
import com.fancylynn.insticator.model.*;
import com.fancylynn.insticator.util.QuestionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lynn on 2018/9/15.
 */

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private MatrixDao matrixDao;

    @Autowired
    private MatrixOptionDao matrixOptionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionResponseDao questionResponseDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private QuestionUtil questionUtil;

    // Service for giving back all the existing questions in the database
    public List<QuestionDto> getAllQuestions() {
        List<Question> questionList = (List) questionDao.findAll();
        List<QuestionDto> result = new ArrayList<>();

        for (Question q : questionList) {
           result.add(questionUtil.setValuesForQuestionDto(q));
        }

        return result;
    }

    // Save user's answer to a specific question
    public void saveQuestionResponse(
            ResponseDto responseDto
    ) throws EntityNotFoundException,IllegalArgumentException {
        QuestionResponse curtResponse = new QuestionResponse();

        // Check whether the response is empty
        String response = responseDto.getResponse();
        if (response == null || response.length() == 0) {
            throw new IllegalArgumentException("Response cannot be empty!");
        }
        curtResponse.setQuestionAns(response);

        // Check whether the question can be found in the pool
        Question question = questionDao.findByQuestionId(responseDto.getQuestionId());
        if (question == null) {
            throw new EntityNotFoundException("Question not found!");
        }
        curtResponse.setQuestions(question);

        // Check whether the user can be found in the db
        User user = userDao.findByUserId(responseDto.getUserId());
        if (user == null) {
            throw new EntityNotFoundException("User not found!");
        }
        curtResponse.setUsers(user);

        questionResponseDao.save(curtResponse);
    }

    // Create a new question
    public QuestionDto createNewQuestion(
            CreateQuestionDto createQuestionDto
    ) throws IllegalArgumentException{
        // Make sure that the option number is correct
        // According to the number requests of different question type
        List<String> options = createQuestionDto.getOptions();
        Map<String, List<String>> matrixOption = createQuestionDto.getMatrixOptions();
        switch (createQuestionDto.getQuestionType()) {
            case "trivia":
                if (options == null || options.size() < 2 || options.size() > 4) {
                    throw new IllegalArgumentException("Trivia must have 2 - 4 options!");
                }
                break;

            case "poll":
                if (options == null || options.size() < 2 || options.size() > 4) {
                    throw new IllegalArgumentException("Poll must have 2 - 4 options!");
                }
                break;

            case "checkbox":
                if (options == null || options.size() < 1 || options.size() > 10) {
                    throw new IllegalArgumentException("Checkbox must have 1 - 10 options!");
                }
                break;

            case "matrix":
                if (matrixOption == null || matrixOption.size() != 2) {
                    throw new IllegalArgumentException("Matrix must have 2 dimensions of options!");
                }
                for (String key : matrixOption.keySet()) {
                    if (matrixOption.get(key) == null || matrixOption.get(key).size() == 0) {
                        throw new IllegalArgumentException("Matrix options cannot be empty!");
                    }
                }
                break;

            default:
                break;
        }

        // Create question
        Question newQuestion = new Question();
        newQuestion.setQuestionContent(createQuestionDto.getQuestionContent());
        newQuestion.setQuestionType(typeDao.findByTypeName(createQuestionDto.getQuestionType()));
        if (createQuestionDto.getQuestionType().equals("trivia")) {
            newQuestion.setCorrectOption(createQuestionDto.getCorrectOption());
        }
        // Save and get the new question for storing in matrix and option table
        Question q = questionDao.save(newQuestion);

        // Create options
        // Loop through the the list of options
        List<String> newOptions = createQuestionDto.getOptions();
        if (newOptions != null && newOptions.size() > 0) {
            for (String op : newOptions) {
                Option newOption = new Option();
                newOption.setOption_content(op);
                newOption.setQuestions(q);
                optionDao.save(newOption);
            }
        }


        // Create matrix and matrix options
        Map<String, List<String>> newMatrixOptions = createQuestionDto.getMatrixOptions();
        if (newMatrixOptions != null && newMatrixOptions.size() > 0) {
            for (String key: newMatrixOptions.keySet()) {
                // Create new matrix for each dimension
                Matrix newMatrix = new Matrix();
                newMatrix.setMatrixContent(key);
                newMatrix.setMatrixQuestions(q);
                // Save and get the new matrix for storing in matrix_option table
                Matrix m = matrixDao.save(newMatrix);

                // Create matrix options
                for (String op : newMatrixOptions.get(key)) {
                    MatrixOption newMatrixOption = new MatrixOption();
                    newMatrixOption.setMatrixOptionContent(op);
                    newMatrixOption.setMatrix(m);
                    matrixOptionDao.save(newMatrixOption);
                }
            }
        } else {
            System.out.println("No matrix detected.");
        }


        // Return the created new question
        return questionUtil.setValuesForQuestionDto(q);

    }


}
