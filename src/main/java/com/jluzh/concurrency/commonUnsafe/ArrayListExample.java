package com.jluzh.concurrency.commonUnsafe;

import com.jluzh.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class ArrayListExample {
    private static int clientRequest = 5000;

    private static int threadTotal = 200;

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientRequest);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientRequest; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    log.error("exception: {}", e);
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
