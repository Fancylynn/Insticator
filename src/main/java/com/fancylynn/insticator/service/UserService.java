package com.fancylynn.insticator.service;

import com.fancylynn.insticator.dao.*;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.model.Matrix;
import com.fancylynn.insticator.model.MatrixOption;
import com.fancylynn.insticator.model.Question;
import com.fancylynn.insticator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lynn on 2018/9/16.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private MatrixDao matrixDao;

    @Autowired
    private MatrixOptionDao matrixOptionDao;

    // Return the questions list based on previous user info if user exists
    public List<QuestionDto> getUserQuestionList(Integer rollingPeriod, String ipAddress) {
        List<QuestionDto> userQuestions = new ArrayList<>();
        // Get the current user based on ip address
        User curtUser = userDao.findByIpAddress(ipAddress);
        // Get the question starting point
        Long startPoint = curtUser.getQuestion_start();
        // Get the number of questions list based on rolling period
        for (Long startIdx = startPoint; startIdx < startPoint + rollingPeriod; startIdx++) {
            Question curt = questionDao.findByQuestionId(startIdx);
            QuestionDto temp = new QuestionDto();
            temp.setId(curt.getQuestionId());
            temp.setContent(curt.getQuestion_content());
            temp.setType(curt.getQuestionType().getType_name());
            temp.setCorrectOption(curt.getCorrectOption());
            if (!curt.getQuestionType().getType_name().equals("matrix")) {
                temp.setOptions(optionDao.findByQuestions_QuestionId(curt.getQuestionId()));
            } else {
                List<Matrix> matrix = matrixDao.findByMatrixQuestions_QuestionId(curt.getQuestionId());
                Map<String, List<MatrixOption>> matrixOptions = new HashMap<>();
                for (Matrix m : matrix) {
                    System.out.println(matrixOptionDao.findByMatrix_MatrixId(m.getMatrixId()).size());
                    System.out.println(m.getMatrixId());
                    matrixOptions.put(m.getMatrixContent(), matrixOptionDao.findByMatrix_MatrixId(m.getMatrixId()));
                }
                temp.setMatrixOptions(matrixOptions);
            }

            userQuestions.add(temp);
        }

        // Update user starting point in the database
        curtUser.setQuestion_start(startPoint + rollingPeriod);
        userDao.save(curtUser);

        return userQuestions;

    }
}
