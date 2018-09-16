package com.fancylynn.insticator.dao;

import com.fancylynn.insticator.model.MatrixOption;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lynn on 2018/9/16.
 */
public interface MatrixOptionDao extends CrudRepository<MatrixOption, Long> {
    List<MatrixOption> findByMatrix_MatrixId(Long matrixId);
}
