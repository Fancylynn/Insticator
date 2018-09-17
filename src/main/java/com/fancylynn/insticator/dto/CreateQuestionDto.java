package com.fancylynn.insticator.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Lynn on 2018/9/16.
 */
public class CreateQuestionDto {
    private String questionContent;
    private String questionType;
    private List<String> options;
    private String correctOption;
    private Map<String, List<String>> matrixOptions;

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public Map<String, List<String>> getMatrixOptions() {
        return matrixOptions;
    }

    public void setMatrixOptions(Map<String, List<String>> matrixOptions) {
        this.matrixOptions = matrixOptions;
    }
}
