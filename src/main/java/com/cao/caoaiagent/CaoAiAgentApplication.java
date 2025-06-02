package com.cao.caoaiagent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cao.caoaiagent.mapper")
public class CaoAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaoAiAgentApplication.class, args);
    }

}
