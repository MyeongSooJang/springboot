package com.bs.boot.boottest.common.commandline;

import com.bs.boot.boottest.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@Configuration
public class CommonConfig {
    @Bean
    public CommandLineRunner commandLineRunner(WebApplicationContext context, MemberService service) {
        return args->{
            log.info(context.getServletContext().getRealPath("/"));
            service.findAllMembers();

        };
    }
}
