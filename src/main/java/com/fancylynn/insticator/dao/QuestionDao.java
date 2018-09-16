package com.fancylynn.insticator.dao;

import com.fancylynn.insticator.model.Question;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lynn on 2018/9/15.
 */
public interface QuestionDao extends CrudRepository<Question, Long>{
    Question findByQuestionId(Long questionId);
}
