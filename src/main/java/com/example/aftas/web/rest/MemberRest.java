package com.example.aftas.web.rest;

import com.example.aftas.domain.Member;
import com.example.aftas.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberRest {
    private final MemberService memberService;

    public MemberRest(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getMemberById(@PathVariable Long id){
        Member member = memberService.getMemberById(id);
        return null;
    }

    @PostMapping
    public ResponseEntity addMember(@PathVariable Member member) {
        Member member1 = memberService.addMember(member);
            return null;

    }

    @GetMapping
    public ResponseEntity searchMember(@RequestBody String name) {
        List<Member> members = memberService.searchMember(name);
       return null;
    }
}
