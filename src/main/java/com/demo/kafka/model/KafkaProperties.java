package com.demo.kafka.model;

/**
 * kafka 配置信息
 * @author Air
 * @version Id: KafkaProperties.java, v 0.1 2017.5.20 10:53 Air Exp $$
 */
public class KafkaProperties {

    public static final String zookeeperConnectionTimeout = "60000";
    public static final String socketTimeout = "30000";
    public static final String serializerClass = "com.demo.kafka.serialize.MessageSerializer";
    public static final String produceRetries = "1";
}
