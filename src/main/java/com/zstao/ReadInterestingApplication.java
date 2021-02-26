package com.zstao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zstao.mapper")
public class ReadInterestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadInterestingApplication.class, args);
    }

}
