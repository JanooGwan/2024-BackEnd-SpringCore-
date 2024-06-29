package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Member;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.domain.Article;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public void create(Article article) {
        articleRepository.save(article);
    }


    @Transactional
    public Article update(Article article) {
        return articleRepository.save(article);
    }


    @Transactional
    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
