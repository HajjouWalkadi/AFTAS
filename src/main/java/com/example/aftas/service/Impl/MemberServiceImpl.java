package com.example.aftas.service.Impl;

import com.example.aftas.domain.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static org.springframework.data.domain.PageRequest.of;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member addMember(Member member) {

        // add accession date and today's date
        //member.setAccessionDate(LocalDate.now());
        member.setReferenceNumber(new Random().nextInt(100000,999999));
        return memberRepository.save(member);
    }

    @Override
    public List<Member> searchByMemberNameOrNumber(String search) {
        return memberRepository.searchByMemberNameOrNumber(search);
    }



    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> getAllMembersPaginated(Pageable pageable) {
        return memberRepository.findAll(pageable).getContent();
    }

    /*  public List<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(of(pageable.getPageNumber(),pageable.getPageSize())).stream().toList();
    }*/


    @Override
    public Member updateMember(Member member, Long id) {
        Member existingMember = getMemberById(id);
        existingMember.setName(member.getName());
        existingMember.setFamilyName(member.getFamilyName());
        existingMember.setAccessionDate(member.getAccessionDate());
        existingMember.setNationality(member.getNationality());
        existingMember.setIdentityNumber(member.getIdentityNumber());
        existingMember.setIdentityDocumentType(member.getIdentityDocumentType());

        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);

    }
}
