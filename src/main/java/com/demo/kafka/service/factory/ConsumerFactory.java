package com.demo.kafka.service.factory;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.demo.kafka.model.KafkaProperties;
import com.demo.kafka.model.TopicInfo;
import com.demo.kafka.tools.IPUtil;
import com.google.common.collect.Maps;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import lombok.extern.slf4j.Slf4j;

/**
 * 生成不同的 consumer
 * @author Air
 * @version Id: ConsumerFactory.java, v 0.1 2017.5.20 13:37 Air Exp $$
 */
@Slf4j
public class ConsumerFactory {

    /**
     * 缓存topic consumer
     */
    public static Map<String, ConsumerConnector> consumerMap = Maps.newHashMap();

    /**
     * 初始化 producer
     *
     * @param topicInfo topic信息
     */
    public static void initConsumer(TopicInfo topicInfo) {
        ConsumerConnector connector = Consumer
            .createJavaConsumerConnector((new ConsumerConfig(getProperty(topicInfo))));
        consumerMap.put(topicInfo.getTopicName(), connector);
    }

    /**
     * 获取缓存的 connector
     *
     * @param topicInfo topic信息
     * @return connector
     */
    public static ConsumerConnector getConnector(TopicInfo topicInfo, String traceLogId) {
        if (consumerMap.get(topicInfo.getTopicName()) == null) {
            initConsumer(topicInfo);
        }
        log.debug("traceLogId:{} ", traceLogId);
        return consumerMap.get(topicInfo.getTopicName());
    }

    private static Properties getProperty(TopicInfo topicInfo) {
        Properties props = new Properties();
        props.put("zookeeper.connect", topicInfo.getZkAddress());
        props.put("group.id", topicInfo.getGroupName());
        String flag = IPUtil.getLocalIP() + "_" + topicInfo.getTopicName() + "_"
                      + UUID.randomUUID().toString();
        props.put("client.id", flag);
        props.put("consumer.id", flag);
        props.put("zookeeper.connection.timeout.ms", KafkaProperties.zookeeperConnectionTimeout);
        props.put("auto.commit.interval.ms", "3000");
        log.debug("consumer Properties:{}", props);
        return props;
    }
}
