package com.bs.boot.boottest.member.model.dto;

import com.bs.boot.boottest.member.model.entity.MemberEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    @Id
    private String userId;
    private String password;
    private String userName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String[] hobby;
    private Date enrollDate;

    public MemberEntity convert(){
        return MemberEntity.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .age(age)
                .gender(gender)
                .email(email)
                .phone(phone)
                .address(address)
                .hobby(hobby!=null?String.join(",", hobby):null)
                .enrollDate(enrollDate)
                .build();
    }
}
