package com.demo.kafka.tools;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * 整数循环
 * @author Air
 * @version Id: IntegerCycle.java, v 0.1 2017.5.20 11:13 Air Exp $$
 */
@Slf4j
public class IntegerCycle {

    /**
     * (0 到 endNum 循环 每次加1)
     * @param endNum 循环的最大数
     * @return 循环的索引
     */
    public static int getIndex(AtomicInteger atomicInteger, int endNum) {
        if (atomicInteger.get() >= endNum - 1) {
            return atomicInteger.getAndSet(0);
        }
        return atomicInteger.getAndIncrement();
    }
}
