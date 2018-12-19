package com.starlux.custom_exectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    private static CountDownLatch appExitLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        new AnnotationConfigApplicationContext(BeanConfiguration.class);
        appExitLatch.await(10, TimeUnit.SECONDS);
        log.info("~ The End ~");
    }
}
