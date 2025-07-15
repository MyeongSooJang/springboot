package com.bs.boot.boottest.member.model.dao;

import com.bs.boot.boottest.member.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,String> {
}
