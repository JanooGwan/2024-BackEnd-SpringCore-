package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.controller.dto.response.ArticleResponse;
import com.example.demo.domain.Board;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.domain.Article;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleResponse> getAll(){
        return articleRepository.findAllArticles();
    }

    public Optional<Article> getById(Long id) {
        return articleRepository.findById(id);
    }

    public List<ArticleResponse> getByBoardId(Long id) {
        return articleRepository.findByBoardId(id);
    }

    @Transactional
    public ArticleResponse create(Article article) {
        Article saved = articleRepository.save(article);
        return new ArticleResponse(saved.getId(), saved.getTitle(), saved.getContent(), saved.getAuthor().getName(), saved.getBoard().getName(),  saved.getCreated_date(), saved.getUpdated_date());
    }


    @Transactional
    public Article update(Article article) {
        return articleRepository.save(article);
    }


    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
