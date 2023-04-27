package com.taskcodee.server.APIs;

public class ApiError {

    private String message;
    private String debugMessage;

    public ApiError() {
    }

    public ApiError(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
