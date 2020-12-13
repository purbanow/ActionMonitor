package com.urban.BusinessApp.exception;

public class ErrorDetails {
    private String message;
    private String details;

    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
