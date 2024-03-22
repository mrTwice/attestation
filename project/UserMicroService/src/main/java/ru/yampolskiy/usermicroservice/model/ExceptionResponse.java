package ru.yampolskiy.usermicroservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {

    private String packageName;
    private String exceptionType;
    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String packageName, String exceptionType, String message) {
        this.packageName = packageName;
        this.exceptionType = exceptionType;
        this.message = message;
    }

}