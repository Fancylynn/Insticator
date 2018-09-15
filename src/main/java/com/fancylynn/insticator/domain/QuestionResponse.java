package com.fancylynn.insticator.domain;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/15.
 */
@Entity
public class QuestionResponse {
    @Id
    @Column(name = "user_uuid", nullable = false, updatable = false)
    private Long user_uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid")
    @MapsId
    private User users;

    @Id
    @Column(name = "question_id", nullable = false, updatable = false)
    private Long question_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    @MapsId
    private Question questionResponse;

    @Column(name = "user_answer")
    private  String user_answer;

    public QuestionResponse(User users, Question questionResponse) {
        this.users = users;
        this.questionResponse = questionResponse;
    }

    public Long getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(Long user_uuid) {
        this.user_uuid = user_uuid;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Question getQuestionResponse() {
        return questionResponse;
    }

    public void setQuestionResponse(Question questionResponse) {
        this.questionResponse = questionResponse;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }
}
