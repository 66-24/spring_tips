package com.starlux.custom_exectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.Executors;

@Slf4j
public class Greeter {
private final String name;

    public Greeter(String name) {
        this.name = name;
    }

    @Scheduled(fixedDelay = 1000L)
    public void greet() {
        log.info("Hi, " + name);
        throw new RuntimeException("Some Exception");
    }
}
