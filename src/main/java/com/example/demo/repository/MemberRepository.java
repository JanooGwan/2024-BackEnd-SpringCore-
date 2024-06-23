package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContexts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Member save(Member member) {
        if (member.getId() == null) {
            entityManager.persist(member);
            return member;
        } else {
            return entityManager.merge(member);
        }
    }

    public Member findById(Long id) {
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll() {
        return entityManager.createQuery("from Member", Member.class).getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Member member = entityManager.find(Member.class, id);
        if (member != null) {
            entityManager.remove(member);
        }
    }
}