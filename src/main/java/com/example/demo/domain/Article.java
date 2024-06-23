package com.example.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long author_id;

    private Long board_id;

    private String title;

    private String content;

    private LocalDateTime created_date;

    private LocalDateTime modified_date;

    protected Article() {
    }

    public Article(Long author_id, Long board_id, String title, String content) {
        this.author_id = author_id;
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.created_date = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public LocalDateTime getModified_date() {
        return modified_date;
    }

    @PrePersist
    protected void onCreate() {
        this.created_date = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modified_date = LocalDateTime.now();
    }

    public void update(Long board_id, String title, String content) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.modified_date = LocalDateTime.now();
    }
}