package com.bs.boot.boottest.controller;

import com.bs.boot.boottest.target.Target1;
import com.bs.boot.boottest.target.Target2;
import com.bs.boot.boottest.target.Target3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@Controller
@Slf4j
public class JpsController {
    // 이건 왜 안나오는걸까요...
    private final Target1 target1;
    private final Target2 target2;
    private final Target3 target3;

    @RequestMapping("/")
    public String index(){
        log.debug("{}",target1);
        log.debug("{}",target2);
        log.debug("{}",target3);
        return "index";
    }
}
