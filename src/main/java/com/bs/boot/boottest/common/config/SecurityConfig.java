package com.bs.boot.boottest.common.config;

import com.bs.boot.boottest.member.model.dao.MemberRepository;
import com.bs.boot.boottest.security.AuthenticationDBProvider;
import com.bs.boot.boottest.security.MyAccessDenied;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

//    private final MemberRepository memberRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   private final AuthenticationDBProvider dbProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(web -> web.disable())
                .formLogin(formLogin -> {
                    formLogin
                            .loginProcessingUrl("/login.do")
                            .successForwardUrl("/success.do")
                            .failureForwardUrl("/fail.do");
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/logout.do");
                })
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                            .requestMatchers("/resources/**").permitAll()
                            .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                            .requestMatchers("/demos").permitAll()
                            .requestMatchers("/demos/**").permitAll()
                            .anyRequest().authenticated();
                })
                .authenticationProvider(dbProvider)
                .exceptionHandling(handle -> {
                    handle.accessDeniedHandler(new MyAccessDenied());
                })
                .build();
    }
}
