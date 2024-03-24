package ru.yampolskiy.taskmicroservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionData{

    private String packageName;
    private String exceptionType;
    private String message;

    public ExceptionData(String packageName, String exceptionType, String message) {
        this.packageName = packageName;
        this.exceptionType = exceptionType;
        this.message = message;
    }

}