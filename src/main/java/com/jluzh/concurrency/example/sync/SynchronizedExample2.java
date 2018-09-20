package com.jluzh.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {
    //修饰一个类
    public static void test01(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test01 {} - {}", j, i);
            }
        }
    }

    // 修饰一个静态方法
    public static synchronized void test02(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test02 {} - {}", j, i);
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            SynchronizedExample2.test01(1);
        });
        executorService.execute(() -> {
            SynchronizedExample2.test01(2);
        });

    }
}
