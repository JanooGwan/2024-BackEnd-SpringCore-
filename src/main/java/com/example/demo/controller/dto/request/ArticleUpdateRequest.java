package com.example.demo.controller.dto.request;

public record ArticleUpdateRequest(
    Long board_id,
    String title,
    String content
) {

}
