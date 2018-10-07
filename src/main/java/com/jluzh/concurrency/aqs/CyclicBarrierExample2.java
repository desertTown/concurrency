package com.jluzh.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exeption", e);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                    log.error("exeption", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            cyclicBarrier.await(2000,  TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            log.warn("BarrierException", e);
        }
        log.info("{} is continue..");
    }
}
