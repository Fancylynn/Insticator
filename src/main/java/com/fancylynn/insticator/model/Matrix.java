package com.fancylynn.insticator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lynn on 2018/9/15.
 */

// Matrix table: for the info related to matrix questions
@Entity
public class Matrix {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="matrix_id", nullable = false, updatable = false)
    private Long matrixId;

    @OneToMany(mappedBy = "matrix", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MatrixOption> matrixOptions = new HashSet<>();

    @Column(name="matrix_content")
    private String matrixContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_id_fk")
    private Question matrixQuestions;

    public Long getMatrixId() {
        return matrixId;
    }

    public void setMatrixId(Long matrixId) {
        this.matrixId = matrixId;
    }

    public String getMatrixContent() {
        return matrixContent;
    }

    public void setMatrixContent(String matrixContent) {
        this.matrixContent = matrixContent;
    }

    public Question getMatrixQuestions() {
        return matrixQuestions;
    }

    public void setMatrixQuestions(Question matrixQuestions) {
        this.matrixQuestions = matrixQuestions;
    }

    public Set<MatrixOption> getMatrixOptions() {
        return matrixOptions;
    }

    public void setMatrixOptions(Set<MatrixOption> matrixOptions) {
        this.matrixOptions = matrixOptions;
    }
}
