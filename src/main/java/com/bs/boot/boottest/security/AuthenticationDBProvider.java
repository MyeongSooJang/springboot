package com.bs.boot.boottest.security;

import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationDBProvider implements AuthenticationProvider {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName(); // 사용자 입력 아이디
        String password = (String)authentication.getCredentials(); // 사용자 입력 비밀번호
        MemberDTO loginMember = memberRepository.findById(userId)
                .orElseThrow(()->{
                  throw new BadCredentialsException("아이디나 패스워드가 일치하지 않습니다.");
                }).convert();
        if(!bCryptPasswordEncoder.matches(password,loginMember.getPassword())){
                throw new BadCredentialsException("아이디나 패스워드가 일치하지 않습니다.");

        }
        return new UsernamePasswordAuthenticationToken
                (authentication.getPrincipal(),authentication.getCredentials(),loginMember.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
