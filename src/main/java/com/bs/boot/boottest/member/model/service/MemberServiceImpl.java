package com.bs.boot.boottest.member.model.service;

import com.bs.boot.boottest.common.token.JWTTokenUtility;
import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
    private final JWTTokenUtility tokenutil;

    @Override
    public MemberDTO findMemberById(String userId) {
        return memberRepository.findById(userId)
                .orElse(MemberEntity.builder().build()).convert();
    }

    @Override
    public List<MemberDTO> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberEntity::convert).toList();
    }

    public List<MemberDTO> findAllMemberPaging(Integer cPage,Integer numPerpage){
        Pageable page= PageRequest.of(cPage-1,numPerpage,
                Sort.by(Sort.Direction.DESC,"enrollDate"));

        return memberRepository.findAll(page).stream()
                .map(MemberEntity::convert).toList();
    }

    @Override
    public List<MemberDTO> findAllMamberPaging(Pageable pageable) {
        return memberRepository.findAll(pageable).stream()
                .map(MemberEntity::convert).toList();
    }

    @Override
    public String loginToken(MemberDTO memberDTO) {
        MemberDTO m = findMemberById(memberDTO.getUserId());
        if(m.getUserId()!=null && encoder.matches(memberDTO.getPassword(),m.getPassword())){
            return tokenutil.generateToken(m);
        } else {
            throw new BadCredentialsException("인증 실패:(");
        }
    }
}
