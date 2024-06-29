package com.example.demo.controller.dto.response;

import java.time.LocalDateTime;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public interface ArticleResponse {
    Long getId();
    String getTitle();
    String getContent();
    BoardResponse getBoard();

}
