package com.bs.boot.boottest.member.model.dto;

import com.bs.boot.boottest.member.model.entity.MemberEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO implements UserDetails {

    @Id
    private String userId;
    private String password;
    private String name;
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
                .userName(name)
                .age(age)
                .gender(gender)
                .email(email)
                .phone(phone)
                .address(address)
                .hobby(hobby!=null?String.join(",", hobby):null)
                .enrollDate(enrollDate)
                .build();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(userId.equals("admin")){
            SimpleGrantedAuthority simpleGrantedAuthority
                    = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        SimpleGrantedAuthority simpleGrantedAuthority
                = new SimpleGrantedAuthority("ROLE_USER");
        grantedAuthorities.add(simpleGrantedAuthority);

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return "userId";
    }
}
