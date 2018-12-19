package com.starlux.custom_exectors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.ThreadFactory;

@Slf4j
class ExceptionHandlingThreadPoolExecutor extends java.util.concurrent.ScheduledThreadPoolExecutor {


    static ExceptionHandlingThreadPoolExecutor newFixedThreadPool(int numThreads, ThreadFactory threadFactory) {
        return new ExceptionHandlingThreadPoolExecutor(numThreads, threadFactory);

    }

    private ExceptionHandlingThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }


    /* Copied from ThreadPoolExecutor
     *
     * */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
/*
        https://bugs.java.com/bugdatabase/view_bug.do?bug_id=7146994

*/
        if (t == null && r instanceof Future<?> &&  ((Future<?>)r).isDone()) {
            try {
                Object result = ((Future<?>) r).get();
                result=null;
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException e) {
                t = e.getCause();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                log.error("Something went wrong", e);
            }
        }
        /*Use DropWizard */
        if (t != null) {
            System.out.println(t);
        }

    }

}
