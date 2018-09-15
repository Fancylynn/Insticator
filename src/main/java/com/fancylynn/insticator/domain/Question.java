package com.fancylynn.insticator.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lynn on 2018/9/12.
 */
@Entity
@Table(name="question_pool")
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="question_id", nullable = false, updatable = false)
    private Long question_id;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private Set<Option> options = new HashSet<>();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private Set<Matrix> matrixs = new HashSet<>();

    @OneToMany(mappedBy = "questionResponse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionResponse> questionResponses = new HashSet<>();

    private String question_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_type")
    private QuestionType questionType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "questionCorrectAnswer")
    private Option questionCorrectOption;

    public Question(QuestionType questionType, Option questionCorrectOption) {
        this.questionType = questionType;
        this.questionCorrectOption = questionCorrectOption;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<Matrix> getMatrixs() {
        return matrixs;
    }

    public void setMatrixs(Set<Matrix> matrixs) {
        this.matrixs = matrixs;
    }

    public Set<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Option getQuestionCorrectOption() {
        return questionCorrectOption;
    }

    public void setQuestionCorrectOption(Option questionCorrectOption) {
        this.questionCorrectOption = questionCorrectOption;
    }
}
