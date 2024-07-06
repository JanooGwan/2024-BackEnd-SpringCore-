package com.example.demo.controller.dto.jwt;

public record JWTToken (
    String accessToken, String refreshToken
) {

}