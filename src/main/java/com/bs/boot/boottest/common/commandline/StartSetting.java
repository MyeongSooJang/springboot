package com.bs.boot.boottest.common.commandline;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
@Order(2)
@Component
@RequiredArgsConstructor
@Slf4j
public class StartSetting implements CommandLineRunner {
    private final Environment environment;
    private final WebApplicationContext webApplicationContext;

    @Override
    public void run(String... args) throws Exception {
      log.info("activeProfile=" + environment.getProperty("spring.profiles.active"));
      log.info("database=" + environment.getProperty("spring.datasource.url"));
      log.info("database=" + environment.getProperty("spring.datasource.username"));
      log.info(webApplicationContext.getServletContext().getRealPath("/"));

      Arrays.stream(webApplicationContext.getBeanDefinitionNames()).filter(n->n.contains("View")).forEach(System.out::println);
    }
}
