package com.example.demo.repository;

import com.example.demo.controller.dto.request.BoardCreateRequest;
import com.example.demo.controller.dto.request.MemberCreateRequest;
import com.example.demo.controller.dto.response.BoardResponse;
import com.example.demo.controller.dto.response.MemberResponse;
import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<MemberResponse> getById(long id);
    Optional<MemberCreateRequest> getRequestById(long id);
}
