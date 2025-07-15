package com.bs.boot.boottest.member.model.service;

import com.bs.boot.boottest.member.model.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO findMemberById(String userId);
    List<MemberDTO> findAllMembers();

}
