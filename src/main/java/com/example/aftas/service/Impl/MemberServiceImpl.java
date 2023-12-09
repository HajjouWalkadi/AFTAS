package com.example.aftas.service.Impl;

import com.example.aftas.domain.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public List<Member> searchMember(String name) {
        return null;
    }

    @Override
    public Member updateMember(Member member, Long id) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}
