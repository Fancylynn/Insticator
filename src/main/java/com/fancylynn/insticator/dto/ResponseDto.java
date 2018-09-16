package com.fancylynn.insticator.dto;

/**
 * Created by Lynn on 2018/9/16.
 */
public class ResponseDto {
    private Long userId;
    private Long questionId;
    private String response;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
