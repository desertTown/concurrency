package com.jluzh.concurrency.publish;

import com.jluzh.concurrency.annotations.NotRecommend;
import com.jluzh.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
// 在构造函数未完成构建之前，不可以将其发布，这个例子会导致变量溢出，被其他线程不正确引用
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
