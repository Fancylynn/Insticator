package com.fancylynn.insticator.dao;

import com.fancylynn.insticator.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lynn on 2018/9/16.
 */
public interface UserDao extends CrudRepository<User, Long> {
    User findByIpAddress(String ipAddress);

    User findByUserId(Long userId);
}
