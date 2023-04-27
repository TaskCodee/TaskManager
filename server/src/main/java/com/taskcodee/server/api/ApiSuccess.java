package com.taskcodee.server.api;

public class ApiSuccess {

    private String message;

    public ApiSuccess() {
    }

    public ApiSuccess(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
