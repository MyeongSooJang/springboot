package com.bs.boot.boottest.member.model.entity;

import com.bs.boot.boottest.member.model.dto.MemberDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MEMBER")
public class MemberEntity {
    @Id
    private String userId;
    private String password;
    private String userName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String hobby;
    private Date enrollDate;
    public MemberDTO convert(){
        return MemberDTO.builder()
                .userId(userId)
                .password(password)
                .name(userName)
                .age(age)
                .gender(gender)
                .email(email)
                .phone(phone)
                .address(address)
                .hobby(hobby!=null?hobby.split(","):null)
                .enrollDate(enrollDate)
                .build();
    }
}
