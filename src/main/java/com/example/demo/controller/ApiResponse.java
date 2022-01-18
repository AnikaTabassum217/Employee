package com.example.demo.controller;


public class ApiResponse {
    public boolean success;
    public String message;


    public ApiResponse() {

    }

    public ApiResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setSuccess(Object data) {
        this.success = true;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public void setError(String message) {
        this.success = false;
        this.message = message;

    }
}