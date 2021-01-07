package com.xrc.database;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xrc
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.xrc.database")
public class DatabaseApplication {

    public static void main(String[] args) {
        log.info("start project");
        SpringApplication.run(DatabaseApplication.class, args);
    }

}
