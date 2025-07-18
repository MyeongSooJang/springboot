package com.bs.boot.boottest.common.token;

import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.member.model.dto.MemberDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
@Service
@RequiredArgsConstructor
public class JWTTokenUtility {
//    @Value("${token.issuer}")
    private String issuer ="bslove";

    // 알고리즘을 적용시켜서 비밀키를 만들어주기
    private final SecretKey key =Jwts.SIG.HS512.key().build();
    private final MemberRepository repository;

    // 1. 토큰을 생성하는 메소드
    public String generateToken(MemberDTO dto) {

        // 토큰 유효기간을 설정
        return createToken(dto, Duration.ofSeconds(20));
    }

    private String createToken(MemberDTO dto, Duration duration) {
        Date limit = new Date(new Date().getTime() + duration.toMillis());

        return Jwts.builder()
                .header().add(Map.of("type","jwt")).and()

                .subject(dto.getUserId())   // 간단한 주요정보
                .issuer(issuer)             // 발급자 정보
                .signWith(key)              // 토큰 생성키
                .expiration(limit)          // 유효기간
                .claims(Map.of(             // 추가 사용자 정보를 저장
                        "email",dto.getEmail(),
                        "name", dto.getName()))

                .compact();                 // 생성하기

    }

    // 2. 토큰으로 인증 처리 하는 메소드
    public Authentication getAuthentication(String token) {
        Claims payload = parseToken(token);
        String userId = payload.getSubject();
        String email = payload.get("email",String.class);

        // DB에서 아이디 확인
        MemberDTO dto = repository.findById(userId).orElseThrow(()->{
            throw new BadCredentialsException("인증실패");
        }).convert();

        return new UsernamePasswordAuthenticationToken(dto,dto.getPassword(),dto.getAuthorities());

        // ** 패스워드 확인할 필요 없음 아이디/비밀번호 확인이 되어야지만 이쪽으로 넘어오기 때문에

    }

    public boolean validateToken (String token) {
        try {
            // 검증 처리
            // 서명 검증, 만료 검증, 문법(JWT 구조에 맞는지)
            // 검증이 실패 -> SignatureException, ExpiredJwtException 발생
            Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    private Claims parseToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseClaimsJws(token).getPayload();
        // 토큰이 유효성 검사와 같은 것들을 모두 처리한다.
    }

}
