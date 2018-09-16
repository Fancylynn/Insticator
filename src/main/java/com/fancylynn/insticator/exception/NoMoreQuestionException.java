package com.fancylynn.insticator.exception;

/**
 * Created by Lynn on 2018/9/16.
 */
public class NoMoreQuestionException extends RuntimeException {
    public NoMoreQuestionException(String message, Throwable cause) {
        super(message, cause);
    }
}
