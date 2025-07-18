package com.bs.boot.boottest.common.token;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends GenericFilter {

    private final JWTTokenUtility tokenUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        // 클라이언트가 전달한 token 값을 확인하고, 인증 처리하는 로직을 구현
        // 클라이언트는 requestheader에 token 값을 전달 -> Authorization 키로 전달

        String token = ((HttpServletRequest)servletRequest).getHeader("Authorization");

        if (token != null && tokenUtil.validateToken(token)) {
            // 인증 처리 로직
            Authentication auth = tokenUtil.getAuthentication(token);
            // Security 인증 로직
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
