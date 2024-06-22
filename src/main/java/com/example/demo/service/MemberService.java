package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exceptions.NullValueException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.dto.request.MemberCreateRequest;
import com.example.demo.controller.dto.request.MemberUpdateRequest;
import com.example.demo.controller.dto.response.MemberResponse;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse getById(Long id) {
        Member member = memberRepository.findById(id);

        return MemberResponse.from(member);
    }

    public List<MemberResponse> getAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberResponse::from)
                .toList();
    }

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {
        if (request.name() == null || request.email() == null || request.password() == null) {
            ArrayList<String> nullList = new ArrayList<>();
            if (request.name() == null) nullList.add("이름");
            if (request.email() == null) nullList.add("이메일");
            if (request.password() == null) nullList.add("비밀번호");
            throw new NullValueException("요청에 필요한 항목이 누락됐습니다.", nullList);
        }

        Member member = memberRepository.insert(
                new Member(request.name(), request.email(), request.password())
        );
        return MemberResponse.from(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id);

        memberRepository.deleteById(id);
    }

    @Transactional
    public MemberResponse update(Long id, MemberUpdateRequest request) {
        if (request.name() == null || request.email() == null) {
            ArrayList<String> nullList = new ArrayList<>();
            if (request.name() == null) nullList.add("이름");
            if (request.email() == null) nullList.add("이메일");
            System.out.println(nullList);
            throw new NullValueException("요청에 필요한 항목이 누락됐습니다.", nullList);
        }

        Member member = memberRepository.findById(id);
        member.update(request.name(), request.email());
        memberRepository.update(member);
        return MemberResponse.from(member);
    }
}
