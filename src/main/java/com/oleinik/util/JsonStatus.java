package com.oleinik.util;

public class JsonStatus {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    private String status;
    private String message;

    public JsonStatus() {
    }

    public JsonStatus(String status) {
        this.status = status;
    }

    public JsonStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
