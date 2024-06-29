package com.example.demo.controller.dto.response;

public class MemberResponse {
    Long id;
    String name;
    String email;
    String password;

    public MemberResponse(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return name;
    }

    public String getPassword() {
        return name;
    }
}