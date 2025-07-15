package com.bs.boot.boottest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
@AllArgsConstructor

public class AppSecurity {
    private String token;
    private List<String> role;

}
