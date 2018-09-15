package com.fancylynn.insticator.domain;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/15.
 */
@Entity
public class Matrix {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="matrix_id", nullable = false, updatable = false)
    private Long matrix_id;

    private String matrix_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_id_fk")
    private Question matrixQuestions;
}
