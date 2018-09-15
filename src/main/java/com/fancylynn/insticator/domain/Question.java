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
    private Set<QuestionResponse> questionResponses = new HashSet<>();

    @OneToMany(mappedBy = "matrixQuestions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private Set<Matrix> matrixs = new HashSet<>();

    private String question_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_type")
    private QuestionType questionType;

    public Question(QuestionType questionType) {
        this.questionType = questionType;
    }

}
