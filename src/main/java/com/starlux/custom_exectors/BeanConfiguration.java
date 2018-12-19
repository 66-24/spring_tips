package com.starlux.custom_exectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.*;
import java.util.concurrent.ThreadFactory;

@Configuration
@EnableScheduling
public class BeanConfiguration implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }


    @Bean(destroyMethod = "shutdownNow")
    public ScheduledExecutorService taskExecutor() {
        return ExceptionHandlingThreadPoolExecutor.newFixedThreadPool(2,threadFactory());
//        return Executors.newScheduledThreadPool(2,threadFactory());
    }

    @Bean
    Greeter greeter() {
        return new Greeter("Whoa..Whoa");
    }

    @Bean
    ThreadFactory threadFactory() {
        return new com.starlux.custom_exectors.ThreadFactory("greeter");
//        return Executors.defaultThreadFactory();
    }
}
