package com.bs.boot.boottest.controller;

import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final MemberService service;

    @PostMapping("/login.do")
    public ResponseEntity<?> login(@RequestBody MemberDTO member) {
        try {
            String token = service.loginToken(member);

            return ResponseEntity.ok()
                    .body(Map.of("msg", "로그인성공!","token",token));

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(Map.of("msg", "인증실패"));
        }

    }
}
