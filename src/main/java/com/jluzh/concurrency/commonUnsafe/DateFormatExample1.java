package com.jluzh.concurrency.commonUnsafe;

import com.jluzh.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class DateFormatExample1 {
    private static int clientRequest = 5000;

    private static int threadTotal = 200;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

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
    }

    private static void update() {
        try {
            simpleDateFormat.parse("20180726");
        } catch (ParseException e) {
            log.error("parse exception",  e);
        }
    }
}
