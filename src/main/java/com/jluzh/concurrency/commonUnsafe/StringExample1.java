package com.jluzh.concurrency.commonUnsafe;

import com.jluzh.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@NotThreadSafe
public class StringExample1 {
    private static int clientRequest = 5000;

    private static int threadTotal = 200;

    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientRequest);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientRequest; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    log.error("exception: {}", e);
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", stringBuilder.length() );
    }

    private static void update() {
        stringBuilder.append("1");
    }
}
