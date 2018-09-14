package com.fancylynn.insticator.domain;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/10.
 */

//Table that stores all the user related data
//UUID for each end user
//Question_start represents the starting point of the question pool

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id", nullable = false, updatable = false)
    private Long user_id;
    private Long question_start;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getQuestion_start() {
        return question_start;
    }

    public void setQuestion_start(Long question_start) {
        this.question_start = question_start;
    }
}
