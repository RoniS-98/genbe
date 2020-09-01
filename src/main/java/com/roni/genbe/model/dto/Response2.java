package com.roni.genbe.model.dto;

public class Response2 {
    private String status;
    private String message;
    private Response3 response3;

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

    public Response3 getResponse3() {
        return response3;
    }

    public void setResponse3(Response3 response3) {
        this.response3 = response3;
    }
}
