package com.example.demo.controller.dto.response;

import java.time.LocalDateTime;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String board;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;


    // 생성자
    public ArticleResponse(Long id, String title, String content, String author, String board, LocalDateTime created_date, LocalDateTime updated_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.board = board;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getBoard() {
        return board;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

}