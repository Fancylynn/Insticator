package com.fancylynn.insticator.util;

import com.fancylynn.insticator.dao.MatrixDao;
import com.fancylynn.insticator.dao.MatrixOptionDao;
import com.fancylynn.insticator.dao.OptionDao;
import com.fancylynn.insticator.dto.QuestionDto;
import com.fancylynn.insticator.model.Matrix;
import com.fancylynn.insticator.model.MatrixOption;
import com.fancylynn.insticator.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lynn on 2018/9/16.
 */
@Component
public class QuestionUtil {
    @Autowired
    private OptionDao optionDao;

    @Autowired
    private MatrixDao matrixDao;

    @Autowired
    private MatrixOptionDao matrixOptionDao;

    // Set values for questionDto
    public QuestionDto setValuesForQuestionDto(Question q) {
        QuestionDto returnCreatedQuestion = new QuestionDto();
        returnCreatedQuestion.setId(q.getQuestionId());
        returnCreatedQuestion.setContent(q.getQuestionContent());
        returnCreatedQuestion.setType(q.getQuestionType().getTypeName());
        returnCreatedQuestion.setCorrectOption(q.getCorrectOption());
        if (!q.getQuestionType().getTypeName().equals("matrix")) {
            returnCreatedQuestion.setOptions(optionDao.findByQuestions_QuestionId(q.getQuestionId()));
        } else {
            List<Matrix> matrix = matrixDao.findByMatrixQuestions_QuestionId(q.getQuestionId());
            Map<String, List<MatrixOption>> matrixOptions = new HashMap<>();
            for (Matrix m : matrix) {
                System.out.println(matrixOptionDao.findByMatrix_MatrixId(m.getMatrixId()).size());
                System.out.println(m.getMatrixId());
                matrixOptions.put(m.getMatrixContent(), matrixOptionDao.findByMatrix_MatrixId(m.getMatrixId()));
            }
            returnCreatedQuestion.setMatrixOptions(matrixOptions);
        }

        return returnCreatedQuestion;
    }

}
