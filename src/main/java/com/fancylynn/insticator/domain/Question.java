package com.fancylynn.insticator.domain;

import javax.persistence.*;

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
    private String question_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_type")
    private QuestionType questionType;

    public Question(QuestionType questionType) {
        this.questionType = questionType;
    }

}
