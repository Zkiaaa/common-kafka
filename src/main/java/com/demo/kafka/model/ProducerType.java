package com.demo.kafka.model;

import lombok.*;

/**
 * 消息同步、异步标示
 * @author Air
 * @version Id: ProducerType.java, v 0.1 2017.5.20 10:39 Air Exp $$
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public enum  ProducerType {

    SYNC("sync", "同步消息"),
    ASYNC("async", "异步消息");

    private String syncFlag;
    private String description;
}
