package com.fancylynn.insticator.dto;

import java.util.List;

/**
 * Created by Lynn on 2018/9/15.
 */
public class QuestionDto {
    private Long id;
    private String content;
    private String type;
    private Long correctOption;
    private List<String> options;

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

    public Long getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Long correctOption) {
        this.correctOption = correctOption;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
