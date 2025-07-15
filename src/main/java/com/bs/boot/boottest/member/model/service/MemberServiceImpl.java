package com.bs.boot.boottest.member.model.service;

import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberDTO> findAllMembers() {
        return memberRepository.findAll().stream().map(MemberEntity::convert).toList();
    }

    @Override
    public MemberDTO findMemberById(String userId) {
        return  memberRepository.findById(userId).orElse(MemberEntity.builder().build()).convert();
    }
}
