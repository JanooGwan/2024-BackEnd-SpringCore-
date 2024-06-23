package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContexts;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Board save(Board board) {
        if (board.getId() == null) {
            entityManager.persist(board);
            return board;
        } else {
            return entityManager.merge(board);
        }
    }

    public Board findById(Long id) {
        return entityManager.find(Board.class, id);
    }

    public List<Board> findAll() {
        return entityManager.createQuery("from Board", Board.class).getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Board board = entityManager.find(Board.class, id);
        if (board != null) {
            entityManager.remove(board);
        }
    }
}