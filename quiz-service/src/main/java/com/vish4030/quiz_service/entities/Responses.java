package com.vish4030.quiz_service.entities;

import org.springframework.http.ResponseEntity;

public class Responses {
    private Integer id;
    private String response;


    public Responses() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}