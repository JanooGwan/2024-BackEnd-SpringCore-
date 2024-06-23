
package com.example.demo.service;

import java.util.List;


import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member getById(Long id) {
        return memberRepository.findById(id);
    }

    public Member create(Member member) {
        return memberRepository.save(member);
    }


    public Member update(Long id, Member member) {
        member.setId(id);
        return memberRepository.update(id, member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
