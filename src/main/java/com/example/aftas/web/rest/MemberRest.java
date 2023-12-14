package com.example.aftas.web.rest;

import com.example.aftas.domain.Member;
import com.example.aftas.dto.MemberRequestDTO;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/members")
public class MemberRest {
    private final MemberService memberService;

    public MemberRest(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getMemberById(@PathVariable Long id) {
        return ResponseMessage.ok("Success", memberService.getMemberById(id));

    }
    @GetMapping
    public ResponseEntity getAllMembers() {

           List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return ResponseMessage.notFound("Members not found");
        } else {
            return ResponseMessage.ok("Success", members);
        }
    }

    @GetMapping("/paginate")
    public ResponseEntity getAllMembersPaginate(@RequestParam @DefaultValue("0") Integer page, @RequestParam Integer size) {
        List<Member> members;
        if (size != null)
            members = memberService.getAllMembersPaginated(PageRequest.of(page, size));
        else
            members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return ResponseMessage.notFound("Member not found");
        } else {
            return ResponseMessage.ok("Success", members);
        }
    }

    @PostMapping
    public ResponseEntity addMember(@Valid @RequestBody MemberRequestDTO memberRequestDTO) {
        Member member1 = memberService.addMember(memberRequestDTO.toMember());
        if (member1 == null) {
            return ResponseMessage.badRequest("Member not created");
        } else {
            return ResponseMessage.created("Member added Successfully", member1);
        }
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestBody String name) {
        List<Member> members = memberService.searchByMemberNameOrNumber(name);
        if (members.isEmpty()) {
            return ResponseMessage.notFound("Member not found");
        } else {
            return ResponseMessage.ok("Success", members);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMember(@RequestBody Member member, @PathVariable Long id) {
        Member member1 = memberService.updateMember(member, id);
        if (member1 == null) {
            return ResponseMessage.badRequest("Member not updated");
        } else {
            return ResponseMessage.created("Member updated successfully", member1);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if (member == null) {
            return ResponseMessage.notFound("Member not found");
        } else {
            memberService.deleteMember(id);
            return ResponseMessage.ok("Member deleted successfully", null);
        }
    }
}
