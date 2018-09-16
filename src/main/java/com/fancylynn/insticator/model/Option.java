package com.fancylynn.insticator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/15.
 */

// Option table: for all the options except for matrix options
@Entity
@Table(name = "option_list")
public class Option {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="option_id", nullable = false, updatable = false)
    private Long option_id;

    private String option_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id_fk")
    @JsonIgnore
    private Question questions;

    public Long getOption_id() {
        return option_id;
    }

    public void setOption_id(Long option_id) {
        this.option_id = option_id;
    }

    public String getOption_content() {
        return option_content;
    }

    public void setOption_content(String option_content) {
        this.option_content = option_content;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

}
