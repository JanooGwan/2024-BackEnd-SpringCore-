package com.example.demo.repository;

import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
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

    @Transactional
    public Member update(Long id, Member updatedMember) {
        Member existingMember = entityManager.find(Member.class, id);
        if (existingMember != null) {
            if (updatedMember.getName() != null) {
                existingMember.setName(updatedMember.getName());
            }
            if (updatedMember.getEmail() != null) {
                existingMember.setEmail(updatedMember.getEmail());
            }
            if (updatedMember.getPassword() != null) {
                existingMember.setPassword(updatedMember.getPassword());
            }
            entityManager.merge(existingMember);
            return existingMember;
        }
        return null;
    }
}
