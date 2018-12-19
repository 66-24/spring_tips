package com.starlux.custom_exectors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/*https://github.com/google/guava/blob/master/guava/src/com/google/common/util/concurrent/ThreadFactoryBuilder.java*/

public class ThreadFactory implements java.util.concurrent.ThreadFactory {
    private final String name;
    private AtomicLong counter = new AtomicLong(1);

    ThreadFactory(String name) {
        this.name = name;
    }

    public Thread newThread(Runnable r) {
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
