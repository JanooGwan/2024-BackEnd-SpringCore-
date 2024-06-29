
package com.example.demo.service;

import java.util.List;


import com.example.demo.domain.Article;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> list(){
        return memberRepository.findAll();
    }

    public Member detail(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member create(Member member) {
        return memberRepository.save(member);
    }


    public Member update(Member member) {
        return memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
