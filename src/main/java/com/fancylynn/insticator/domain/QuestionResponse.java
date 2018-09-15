package com.fancylynn.insticator.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lynn on 2018/9/15.
 */
@Entity
public class QuestionResponse {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="question_response_id", nullable = false, updatable = false)
    private Long question_response_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid")
    private User users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question questions;

    private String questionAns;

    public QuestionResponse(User users, Question questions) {
        this.users = users;
        this.questions = questions;
    }

    public Long getQuestion_response_id() {
        return question_response_id;
    }

    public void setQuestion_response_id(Long question_response_id) {
        this.question_response_id = question_response_id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }


}
