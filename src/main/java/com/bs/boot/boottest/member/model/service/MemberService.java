package com.bs.boot.boottest.member.model.service;

import com.bs.boot.boottest.member.model.dto.MemberDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    MemberDTO findMemberById(String userId);
    List<MemberDTO> findAllMembers();
    List<MemberDTO> findAllMemberPaging(Integer cPage, Integer numPerpage);
    List<MemberDTO> findAllMamberPaging(Pageable pageable);

}
