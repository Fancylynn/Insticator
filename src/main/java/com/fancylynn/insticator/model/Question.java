package com.fancylynn.insticator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lynn on 2018/9/12.
 */

// Question table: all the questions in the pool
@Entity
@Table(name="question_pool")
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="questionId", nullable = false, updatable = false)
    private Long questionId;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<QuestionResponse> questionResponses = new HashSet<>();

    @OneToMany(mappedBy = "matrixQuestions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Matrix> matrixs = new HashSet<>();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Option> questionOptions = new HashSet<>();

    private String question_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_type")
    private QuestionType questionType;

    private String correctOption;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Set<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(Set<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public Set<Matrix> getMatrixs() {
        return matrixs;
    }

    public void setMatrixs(Set<Matrix> matrixs) {
        this.matrixs = matrixs;
    }

    public Set<Option> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(Set<Option> questionOptions) {
        this.questionOptions = questionOptions;
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

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
}
