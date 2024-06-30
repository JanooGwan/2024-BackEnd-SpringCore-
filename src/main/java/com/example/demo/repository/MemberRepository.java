package com.example.demo.repository;

import com.example.demo.controller.dto.request.BoardCreateRequest;
import com.example.demo.controller.dto.request.MemberCreateRequest;
import com.example.demo.controller.dto.response.BoardResponse;
import com.example.demo.controller.dto.response.MemberResponse;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT new com.example.demo.controller.dto.response.MemberResponse(member.id, member.name, member.email, member.password) " +
            "FROM Member member")
    List<MemberResponse> findAllMembers();

    @Query("SELECT new com.example.demo.controller.dto.response.MemberResponse(member.id, member.name, member.email, member.password) " +
            "FROM Member member WHERE member.id = :id")
    Optional<Member> findById(@Param("id") Long id);

    Member findMemberById(Long id);
}
