package ru.yampolskiy.usermicroservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private int code;
    private T data;

    public Response(int code, T data){
        this.code = code;
        this.data = data;
    }

}
