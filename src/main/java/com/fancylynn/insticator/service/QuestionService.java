package com.fancylynn.insticator.service;

import com.fancylynn.insticator.dao.QuestionDao;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynn on 2018/9/15.
 */

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public List<QuestionDto> getAllQuestions() {
        List<Question> questionList = (List) questionDao.findAll();
        List<QuestionDto> result = new ArrayList<>();

        for (Question q : questionList) {
           QuestionDto temp = new QuestionDto();
           temp.setId(q.getQuestion_id());
           temp.setContent(q.getQuestion_content());
           temp.setType(q.getQuestionType().getType_name());
           result.add(temp);
        }

        return result;
    }
}
