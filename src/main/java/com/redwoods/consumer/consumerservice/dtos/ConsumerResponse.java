package com.redwoods.consumer.consumerservice.dtos;

import java.util.List;

public class ConsumerResponse {

    private Integer status;

    private  String message;

    private Object data;

    private List<String> errors;

    public ConsumerResponse(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ConsumerResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ConsumerResponse(Integer status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
