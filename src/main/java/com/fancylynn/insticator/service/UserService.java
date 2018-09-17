package com.fancylynn.insticator.service;

import com.fancylynn.insticator.dao.*;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.exception.NoMoreQuestionException;
import com.fancylynn.insticator.model.Matrix;
import com.fancylynn.insticator.model.MatrixOption;
import com.fancylynn.insticator.model.Question;
import com.fancylynn.insticator.model.User;
import com.fancylynn.insticator.util.QuestionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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

    @Autowired
    private QuestionUtil questionUtil;

    @Autowired
    private SessionFactory sessionFactory;

    // Return the questions list based on previous user info if user exists
    public List<QuestionDto> getUserQuestionList(
            Integer rollingPeriod, String ipAddress
    ) throws NoMoreQuestionException, EntityNotFoundException{
        // Build a session
        Session session = sessionFactory.openSession();

        List<QuestionDto> userQuestions = new ArrayList<>();
        // Get the current user based on ip address
        User curtUser = userDao.findByIpAddress(ipAddress);

        // Check whether current user exists in the database or not
        if (curtUser == null) {
            throw new EntityNotFoundException("User not found!");
        }

        // Get the question starting point
        Long startPoint = curtUser.getQuestion_start();
        // Get the number of questions list based on rolling period
        try {
            while (userQuestions.size() < rollingPeriod) {
                // Hibernate criteria for retrieving entites by composing Criterion objects
                // It is an easy approach like search
                // Here, criteria is used to search for next question pointer
                // As questions can be deleted and the id is thus not consecutive
                Criteria criteria = session.createCriteria(Question.class);
                criteria.add(Restrictions.ge("questionId", startPoint));
                criteria.setProjection(Projections.min("questionId"));
                Long returnedId = (Long) criteria.uniqueResult();
//                System.out.println("startPoint id: " + startPoint);
//                System.out.println("return id: " + returnedId);
                if (returnedId == null) {
                    throw new NullPointerException();
                }
                Question curt = questionDao.findByQuestionId(returnedId);
                userQuestions.add(questionUtil.setValuesForQuestionDto(curt));
                startPoint = returnedId + 1L;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            // Check whether the user question list exists
            if (userQuestions.size() == 0) {
                throw new NoMoreQuestionException("No more question for this user", e);
            }
        } finally {
            session.close();
        }

        // Update user starting point in the database
        curtUser.setQuestion_start(startPoint);
        userDao.save(curtUser);

        return userQuestions;

    }

    // Create new user
    public User createNewUser(String ipAddress) throws EntityExistsException{
        // Check if the ip address exists
        if (userDao.findByIpAddress(ipAddress) != null) {
            throw new EntityExistsException("User exists with this ip address!");
        }

        // new user creation
        User newUser = new User();
        newUser.setIpAddress(ipAddress);
        newUser.setQuestion_start(1L);

        userDao.save(newUser);

        return newUser;
    }
}