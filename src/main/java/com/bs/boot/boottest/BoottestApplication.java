package com.bs.boot.boottest;

import com.bs.boot.boottest.common.AppProperties;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;

import java.util.Properties;
@Order(1)
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BoottestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        Properties applicationProperties = new Properties();
        applicationProperties.setProperty("server.port", "9999");
        SpringApplication app = new SpringApplication(BoottestApplication.class);
        app.setDefaultProperties(applicationProperties);
        app.run(args);
        // 프로퍼티설정 우선순위
//          1. setDefaultProperties()로 설정한 것 먼저 사용
//          2. @PropertySource를 설정한 내용 설정
//          3. application.properties / application.yml
//          4. 명령행인자 java -jar *.jar/*.war --server.port=1000

        //SpringApplication.run(BoottestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("commandLineRunner실행!");
    }

}
