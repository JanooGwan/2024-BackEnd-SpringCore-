package com.example.demo.controller.dto.response;

import java.time.LocalDateTime;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record ArticleResponse(
    Long id,
    Long author_id,
    Long board_id,
    String title,
    String content,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime created_date,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime modified_date
) {

    public static ArticleResponse of(Article article, Member member, Board board) {
        return new ArticleResponse(
            article.getId(),
            member.getId(),
            board.getId(),
            article.getTitle(),
            article.getContent(),
            article.getCreated_date(),
            article.getModified_date()
        );
    }
}
