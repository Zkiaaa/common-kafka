package com.demo.kafka.partition;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Air
 * @version Id: FirstPartition.java, v 0.1 2017.5.20 13:23 Air Exp $$
 */
@Slf4j
public class FirstPartition {

    public int partition(Object o, int i) {
        //TODO
        log.debug("分区选择：{}，{}", o, i);
        return 0;
    }
}
