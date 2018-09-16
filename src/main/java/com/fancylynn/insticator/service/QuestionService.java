package com.fancylynn.insticator.service;

import com.fancylynn.insticator.dao.MatrixDao;
import com.fancylynn.insticator.dao.MatrixOptionDao;
import com.fancylynn.insticator.dao.OptionDao;
import com.fancylynn.insticator.dao.QuestionDao;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.model.Matrix;
import com.fancylynn.insticator.model.MatrixOption;
import com.fancylynn.insticator.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
