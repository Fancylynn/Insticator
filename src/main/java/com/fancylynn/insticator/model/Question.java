package com.fancylynn.insticator.model;

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
    private Set<QuestionResponse> questionResponses = new HashSet<>();

    @OneToMany(mappedBy = "matrixQuestions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Matrix> matrixs = new HashSet<>();

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> questionOptions = new HashSet<>();

    private String question_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_type")
    private QuestionType questionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correct_option_id_fk")
    private Option correctOption;

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
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

    public Option getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Option correctOption) {
        this.correctOption = correctOption;
    }
}
