package com.fancylynn.insticator.dto;

import com.fancylynn.insticator.model.MatrixOption;
import com.fancylynn.insticator.model.Option;

import java.util.List;
import java.util.Map;

/**
 * Created by Lynn on 2018/9/15.
 */
public class QuestionDto {
    private Long id;
    private String content;
    private String type;
    private String correctOption;
    private List<Option> options;
    private Map<String, List<MatrixOption>> matrixOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Map<String, List<MatrixOption>> getMatrixOptions() {
        return matrixOptions;
    }

    public void setMatrixOptions(Map<String, List<MatrixOption>> matrixOptions) {
        this.matrixOptions = matrixOptions;
    }
}
