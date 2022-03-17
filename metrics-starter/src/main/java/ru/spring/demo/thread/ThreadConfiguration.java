package ru.spring.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ThreadConfiguration {

    @Bean
    @ConditionalOnMissingBean
    RejectedExecutionHandler rejectedExecutionHandler() {
        return (r, executor) -> log.info("task failed — {}", r);
    }

    @Bean
    public ThreadPoolExecutor restProcessorExecutor(
//            AdjustmentProperties adjustmentProperties,
            RejectedExecutionHandler rejectedExecutionHandler
    ) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                6,
                50,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                new BasicThreadFactory.Builder()
                        .namingPattern("letter-%d")
                        .daemon(true)
                        .priority(Thread.MAX_PRIORITY)
                        .build()
        );

        threadPoolExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);

        threadPoolExecutor.prestartAllCoreThreads();
        return threadPoolExecutor;
    }
}
