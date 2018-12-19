package com.starlux.custom_exectors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadFactory implements java.util.concurrent.ThreadFactory {
    private final String name;
    private final java.util.concurrent.ThreadFactory threadFactory;
    private AtomicLong counter = new AtomicLong(1);

    public ThreadFactory(String name) {
        this.name = name;
        threadFactory = Executors.defaultThreadFactory();
    }

    public Thread newThread(Runnable r) {
//        final Thread thread = threadFactory.newThread(r);
        final Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.setName(name + "-" + counter.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }
}

@Slf4j
class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    public void uncaughtException(Thread t, Throwable e) {
        log.info("Thread: {}", t, e);
    }
}
