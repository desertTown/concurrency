package com.jluzh.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0; i< 10;i++) {
            final int threadCount = i;
            executorService.execute(() -> {
                log.info("task: {}", threadCount);
            });
        }
        executorService.shutdown();
    }
}
