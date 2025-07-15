package com.bs.boot.boottest.controller;

import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public MemberDTO getMember (@PathVariable String id){
        return memberService.findMemberById(id);
    }

    @GetMapping
    public List<MemberDTO> getAllMembers(){
        return memberService.findAllMembers();
    }
}
