package com.jluzh.concurrency.singleton;


import com.jluzh.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
