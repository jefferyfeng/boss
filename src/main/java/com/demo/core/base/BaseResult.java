package com.demo.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
public class BaseResult<T> implements Serializable{
    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)//为空时不序列化
    private T body;

    public BaseResult() {
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
