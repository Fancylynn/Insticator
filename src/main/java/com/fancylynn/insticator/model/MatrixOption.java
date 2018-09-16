package com.fancylynn.insticator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/16.
 */
@Entity
public class MatrixOption {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="matrix_option_id", nullable = false, updatable = false)
    private Long matrixOptionId;

    @Column(name="matrix_option_content")
    private String matrixOptionContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matrix_id")
    @JsonIgnore
    private Matrix matrix;

    public Long getMatrixOptionId() {
        return matrixOptionId;
    }

    public void setMatrixOptionId(Long matrixOptionId) {
        this.matrixOptionId = matrixOptionId;
    }

    public String getMatrixOptionContent() {
        return matrixOptionContent;
    }

    public void setMatrixOptionContent(String matrixOptionContent) {
        this.matrixOptionContent = matrixOptionContent;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
