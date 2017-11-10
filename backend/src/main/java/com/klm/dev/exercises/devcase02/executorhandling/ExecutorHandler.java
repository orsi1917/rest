package com.klm.dev.exercises.devcase02.executorhandling;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ExecutorHandler {
    public static void configureExecutor(ThreadPoolTaskExecutor executor, int corePoolSize, int maxPoolSize, int queueCapacity, int keepAliveSeconds){
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
    }
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
