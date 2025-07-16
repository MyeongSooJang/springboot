package com.bs.boot.boottest.member.model.service;

import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.member.model.dto.MemberDTO;
import com.bs.boot.boottest.member.model.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
}
