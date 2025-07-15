package com.bs.boot.boottest.controller;

import com.bs.boot.boottest.common.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HomeController {
    @Value("${linux.home}")
    private String home;
    @Value("${linux.ip}")
    private String ip;

    @Value("${env.path}")
    private String path;
    @Value("${env.javahome}")
    private String javahome;

    private final Environment environment;


    @RequestMapping("/")
    public String index(){
        log.info(home);
        log.info(ip);
        log.info(path);
        log.info(javahome);
        log.info(environment.getProperty("server.port"));
        log.info(environment.getProperty("spring.banner.location"));
        return "Hello Spring Boot!";
    }
    
    private final AppProperties appProperties;
    
    @GetMapping("/proptest")
    public String proptest(){
        log.info("{}",appProperties);
        log.info("{}",appProperties.getSecurity());
        return "properties 커스터마이징";
    }
}
