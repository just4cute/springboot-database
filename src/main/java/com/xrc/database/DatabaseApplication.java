package com.xrc.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xrc
 */
@SpringBootApplication
@Slf4j
public class DatabaseApplication {

    public static void main(String[] args) {
        log.info("start project");
        SpringApplication.run(DatabaseApplication.class, args);
    }

}
