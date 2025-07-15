package com.bs.boot.boottest.common.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class SpringBootEventHandler {
    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent(ApplicationReadyEvent event) throws Exception {
        log.info("applicationReadyEvent발생");
        log.info(Arrays.toString(event.getArgs()));
        log.info("{}",event.getSpringApplication());

    }
    @EventListener(WebServerInitializedEvent.class)
    public void webServerInitializedEvent(WebServerInitializedEvent event) throws Exception {
        log.info("서버 실행 완료!");
        log.info("{}",event.getWebServer().getPort());

    }

}
