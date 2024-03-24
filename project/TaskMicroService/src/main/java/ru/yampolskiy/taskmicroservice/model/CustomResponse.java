package ru.yampolskiy.taskmicroservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonSerialize
@JsonDeserialize
@Getter
@Setter
public class CustomResponse<T> {
    private int errorCode;
    private  T responseData;
    private ExceptionData responseError;

    public CustomResponse() {
    }

    public CustomResponse(int errorCode, T responseData){
        this.errorCode = errorCode;
        this.responseData = responseData;
        this.responseError = null;
    }

    public CustomResponse( int errorCode, ExceptionData responseError){
        this.errorCode = errorCode;
        this.responseData = null;
        this.responseError = responseError;
    }
}
