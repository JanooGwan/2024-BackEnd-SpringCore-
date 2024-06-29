package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDateTime created_date;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDateTime updated_date;
    
    @Builder
    public Article(Member author_id, Board board_id, String title, String content) {
        this.author = author_id;
        this.board = board_id;
        this.title = title;
        this.content = content;
    }

    public void update(Board board_id, String title, String content) {
        this.board = board_id;
        this.title = title;
        this.content = content;
    }
}