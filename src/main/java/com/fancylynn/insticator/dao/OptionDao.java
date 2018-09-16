package com.fancylynn.insticator.dao;

import com.fancylynn.insticator.model.Option;
import com.fancylynn.insticator.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lynn on 2018/9/15.
 */
public interface OptionDao extends CrudRepository<Option, Long> {
    List<Option> findByQuestions_QuestionId(Long questionIdFk);
}
