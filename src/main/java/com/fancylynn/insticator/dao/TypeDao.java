package com.fancylynn.insticator.dao;

import com.fancylynn.insticator.model.QuestionType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lynn on 2018/9/16.
 */
public interface TypeDao extends CrudRepository<QuestionType, Long> {
    QuestionType findByTypeName(String typeName);
}
