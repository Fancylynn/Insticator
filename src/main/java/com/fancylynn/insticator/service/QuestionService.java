package com.fancylynn.insticator.service;

import com.fancylynn.insticator.dao.*;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.dto.ResponseDto;
import com.fancylynn.insticator.model.*;
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

    // Service for giving back all the existing questions in the database
    public List<QuestionDto> getAllQuestions() {
        List<Question> questionList = (List) questionDao.findAll();
        List<QuestionDto> result = new ArrayList<>();

        for (Question q : questionList) {
           QuestionDto temp = new QuestionDto();
           temp.setId(q.getQuestionId());
           temp.setContent(q.getQuestion_content());
           temp.setType(q.getQuestionType().getType_name());
           temp.setCorrectOption(q.getCorrectOption());
           if (!q.getQuestionType().getType_name().equals("matrix")) {
               temp.setOptions(optionDao.findByQuestions_QuestionId(q.getQuestionId()));
           } else {
               List<Matrix> matrix = matrixDao.findByMatrixQuestions_QuestionId(q.getQuestionId());
               Map<String, List<MatrixOption>> matrixOptions = new HashMap<>();
               for (Matrix m : matrix) {
                   System.out.println(matrixOptionDao.findByMatrix_MatrixId(m.getMatrixId()).size());
                   System.out.println(m.getMatrixId());
                   matrixOptions.put(m.getMatrixContent(), matrixOptionDao.findByMatrix_MatrixId(m.getMatrixId()));
               }
               temp.setMatrixOptions(matrixOptions);
           }

           result.add(temp);
        }

        return result;
    }

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
}
