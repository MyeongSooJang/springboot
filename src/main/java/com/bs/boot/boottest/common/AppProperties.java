package com.bs.boot.boottest.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.regex.Pattern;

@ConfigurationProperties("app.ms")
@Getter
@ToString

public class AppProperties {
    private final String name;
    private final String ip;
    private final int port;

    private final AppSecurity security;

    public AppProperties(String name, String ip, int port, AppSecurity security) {
        this.name = name;
        String reg = "^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$";
        if (!Pattern.matches(reg, ip)){
            throw new IllegalArgumentException("IP 주소 형식이 맞지 않습니다.");
        }
        this.ip = ip;
        if (!(0<=port && port<=65535)){
            throw new IllegalArgumentException("포트범위를 벗어났습니다.");
        }
        this.port = port;
        this.security = security;
    }
}
