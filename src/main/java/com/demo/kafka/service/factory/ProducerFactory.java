package com.demo.kafka.service.factory;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import com.demo.kafka.model.KafkaProperties;
import com.demo.kafka.model.TopicInfo;
import com.demo.kafka.tools.IntegerCycle;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * 产生不同的producer
 * @author Air
 * @version Id: ProducerFactory.java, v 0.1 2017.5.20 11:57 Air Exp $$
 */
@Slf4j
public class ProducerFactory {
    private static AtomicInteger              atomicInteger = new AtomicInteger(0);

    /**
     * 每个 topic 的producer个数
     */
    private final static int                  producerNum   = 1;
    /**
     * 缓存topic producer
     */
    public static Map<String, List<Producer>> topicMap      = Maps.newHashMap();

    public static void initProducer(TopicInfo topicInfo) {
        List<Producer> producerList = Lists.newArrayList();
        for (int i = 0; i < producerNum; i++) {
            producerList.add(new Producer(new ProducerConfig(getProperty(topicInfo))));
            log.debug("initProducer :{},{}", topicInfo.getTopicName(), i);
        }
        topicMap.put(topicInfo.getTopicName(), producerList);
    }

    /**
     * 设置 producer 参数
     * @return Properties
     */
    private static Properties getProperty(TopicInfo topicInfo) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", topicInfo.getBrokerList());
        properties.put("request.required.acks", topicInfo.getAck().getAck());
        properties.put("producer.type", topicInfo.getSyncFlag().getSyncFlag());
        properties.put("serializer.class", KafkaProperties.serializerClass);
        properties.put("message.send.max.retries",
            topicInfo.isRetries() ? KafkaProperties.produceRetries : 0);
        properties.put("request.timeout.ms", "300");

        return properties;
    }

    /**
     * 创建 Producer
     * @param topicInfo topic 信息
     * @return Producer
     */
    public static Producer getProducer(TopicInfo topicInfo) {
        if (topicMap.get(topicInfo.getTopicName()) == null
            || topicMap.get(topicInfo.getTopicName()).size() == 0) {
            initProducer(topicInfo);
        }
        int n = IntegerCycle.getIndex(atomicInteger, producerNum);
        log.debug("index of connector ：{}", n);
        return topicMap.get(topicInfo.getTopicName()).get(n);
    }
}
