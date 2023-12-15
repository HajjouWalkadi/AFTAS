package com.example.aftas.service;

import com.example.aftas.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MemberService {
    Member getMemberById(Long id);
    Member addMember(Member member);
    List<Member> searchByMemberNameOrNumber(String search);
    List<Member> getAllMembers();
    Member updateMember(Member member, Long id);
    void deleteMember(Long id);
}


