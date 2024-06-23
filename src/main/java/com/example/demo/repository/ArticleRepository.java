package com.example.demo.repository;

import com.example.demo.domain.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContexts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
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
    public void deleteById(Long Id) {
        Article article = entityManager.find(Article.class, id);
        if (article != null) {
            entityManager.remove(article);
        }
    }
}
