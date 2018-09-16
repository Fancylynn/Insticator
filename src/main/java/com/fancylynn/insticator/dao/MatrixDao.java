package com.fancylynn.insticator.dao;

import com.fancylynn.insticator.model.Matrix;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lynn on 2018/9/16.
 */
public interface MatrixDao extends CrudRepository<Matrix, Long> {
    List<Matrix> findByMatrixQuestions_QuestionId(Long questionIdFk);
}
