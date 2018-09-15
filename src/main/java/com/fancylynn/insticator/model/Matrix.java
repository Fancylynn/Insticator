package com.fancylynn.insticator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lynn on 2018/9/15.
 */
@Entity
public class Matrix {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="matrix_id", nullable = false, updatable = false)
    private Long matrix_id;

    @OneToMany(mappedBy = "matrixs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> options = new HashSet<>();

    private String matrix_content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_id_fk")
    private Question matrixQuestions;

    public Long getMatrix_id() {
        return matrix_id;
    }

    public void setMatrix_id(Long matrix_id) {
        this.matrix_id = matrix_id;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public String getMatrix_content() {
        return matrix_content;
    }

    public void setMatrix_content(String matrix_content) {
        this.matrix_content = matrix_content;
    }

    public Question getMatrixQuestions() {
        return matrixQuestions;
    }

    public void setMatrixQuestions(Question matrixQuestions) {
        this.matrixQuestions = matrixQuestions;
    }
}
