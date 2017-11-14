package com.klm.dev.exercise.devcase02.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ExecutorHandler {

    public static ThreadPoolTaskExecutor getConfiguredThreadPoolTaskExecutor (int corePoolSize, int maxPoolSize, int queueCapacity, int keepAliveSeconds) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.initialize();
        return executor;
    }
}
