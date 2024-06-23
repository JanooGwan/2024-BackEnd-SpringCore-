package com.example.demo.repository;

import com.example.demo.controller.dto.response.ArticleResponse;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public Article save(Article article) {
        if (article.getId() == null) {
            entityManager.persist(article);
            return article;
        }

        else return entityManager.merge(article);
    }

    public Article findById(Long id) {
        return entityManager.find(Article.class, id);
    }

    public List<Article> findAll() {
        return entityManager.createQuery("from Article", Article.class).getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Article article = entityManager.find(Article.class, id);
        if (article != null) {
            entityManager.remove(article);
        }
    }

    @Transactional
    public Article update(Long id, Article updatedArticle) {
        Article existingArticle = entityManager.find(Article.class, id);
        if (existingArticle != null) {
            if (updatedArticle.getBoard_id() != null) {
                existingArticle.setBoard_id(updatedArticle.getBoard_id());
            }
            if (updatedArticle.getTitle() != null) {
                existingArticle.setTitle(updatedArticle.getTitle());
            }
            if (updatedArticle.getContent() != null) {
                existingArticle.setContent(updatedArticle.getContent());
            }
            entityManager.merge(existingArticle);
            return existingArticle;
        }
        return null;
    }
}