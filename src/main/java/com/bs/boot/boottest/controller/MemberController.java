package com.bs.boot.boottest.controller;

import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public MemberDTO getMember(@PathVariable String id){
        return memberService.findMemberById(id);
    }

    @GetMapping
    public List<MemberDTO> getMembers(){
        log.debug("getMember메소드 실행");

        return memberService.findAllMembers();
    }

    @GetMapping("/page")
//    public List<MemberDTO> getMemberPage(@RequestParam(defaultValue="1") Integer cPage,
//                                         @RequestParam(defaultValue="5") Integer numPerpage){
    // page,size를 매개변수로 전달
    // optional sort = 필드명, 정렬방법(ASC,DESC)
    public List<MemberDTO> getMemberPage(@PageableDefault(size = 3, sort = "age", direction = Sort.Direction.DESC) Pageable pageable, Sort sort){
        return memberService.findAllMamberPaging(pageable);
    }

}
