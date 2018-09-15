package com.fancylynn.insticator.model;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/15.
 */
@Entity
@Table(name = "option_list")
public class Option {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="option_id", nullable = false, updatable = false)
    private Long option_id;

    @OneToOne(mappedBy = "correctOption", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Question questionOption;

    private String option_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id_fk")
    private Question questions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="matrix_id_fk")
    private Matrix matrixs;

    public Long getOption_id() {
        return option_id;
    }

    public void setOption_id(Long option_id) {
        this.option_id = option_id;
    }

    public Question getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(Question questionOption) {
        this.questionOption = questionOption;
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

    public Matrix getMatrixs() {
        return matrixs;
    }

    public void setMatrixs(Matrix matrixs) {
        this.matrixs = matrixs;
    }
}
