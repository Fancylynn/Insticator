package com.fancylynn.insticator.domain;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/10.
 */

//Table that stores all the user related data
//UUID for each end user
//Question_start represents the starting point of the question pool

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;
    private Long question_start;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestion_start() {
        return question_start;
    }

    public void setQuestion_start(Long question_start) {
        this.question_start = question_start;
    }
}
