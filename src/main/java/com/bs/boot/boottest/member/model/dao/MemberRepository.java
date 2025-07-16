package com.bs.boot.boottest.member.model.dao;

import com.bs.boot.boottest.member.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,String> {

    // 쿼리 메소드 선언하기
    // 방식: findBy 필드명[연산자][논리연산자] [필드명연산자][Orderby 필드명 정렬방식](필드 대입할 값,,,,,,)
    // 이름로 검색
    Optional<MemberEntity> findByUserName(String userId);
    // 나이로 검색 매개변수보다 나이가 많은 회원조회
    List<MemberEntity> findByAgeGreaterThan(Integer age);

    // 가입을 기준으로 조회
    // 입력한 일자보다 늦게 가입한 회원조회하기
    List<MemberEntity> findByEnrollDateGreaterThan(Date enrollDate);

    // 아이디*(포함) 와 나이를 기준으로 조회
    List<MemberEntity> findByUserIdContainingAndAgeLessThan(String userId, int age);

    // 정렬해서 가져오기
    // 이름이 포함하고 있는 회원을 가입일순으로 가져오기
    List<MemberEntity> findByUserNameContainingOrderByEnrollDateDesc(String userName);

    // 삭제하기
    List<MemberEntity> deleteByEnrollDateBefore(Date enrollDate);

    // JPQL 구문이용하기
    // @Query 어노테이션을 이용
    @Query("select m from MemberEntity m ")
    List<MemberEntity> selectMemberAll();

    // 매개 변수 처리하기
    @Query("select m from MemberEntity m where m.age between ?1 and ?2 and m.userName like ?3")
    List<MemberEntity> selectMemberByAgeAndUserName(Integer age, Integer age2, String userName);

    @Query("select m from MemberEntity m where m.email like : email")
    List<MemberEntity> selectMemberByEmail(@Param(value = "email") String email);

    // 표준 SQL문 사용하기
    @Query(value = "SELECT * FROM MEMBER",nativeQuery = true)
    List<MemberEntity> selectNativeQueryMember();
}
