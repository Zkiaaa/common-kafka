package com.demo.kafka.service.nospring;

import java.util.List;
import java.util.Random;

import com.demo.kafka.model.TopicInfo;
import com.demo.kafka.service.factory.ProducerFactory;
import com.demo.kafka.thread.SendMsgThread;
import com.demo.kafka.thread.ThreadPool;

import kafka.producer.KeyedMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * kafka消息发送，不依赖spring
 * @author Air
 * @version Id: ProducerNoSpringService.java, v 0.1 2017.5.20 12:17 Air Exp $$
 */
@Slf4j
public class ProducerNoSpringService {

    /**
     * 发送消息集合
     *
     * @param topicInfo topic 信息
     * @param messages  待发送消息
     * @param <T>       消息类型
     */
    public static <T> void sendMessages(TopicInfo topicInfo, List<T> messages, String traceLogId) {
        log.debug("traceLogId:{},topicInfo:{} messages to send :{}", traceLogId, topicInfo,
            messages);
        ProducerFactory.getProducer(topicInfo)
            .send(new KeyedMessage(topicInfo.getTopicName(), messages));
    }

    /**
     * 发送单个消息(随机分配)
     *
     * @param topicInfo topic 信息
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public static <T> void sendMessage(TopicInfo topicInfo, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo, message);
        int random = new Random().nextInt();
        ProducerFactory.getProducer(topicInfo)
            .send(new KeyedMessage(topicInfo.getTopicName(), random, message));
    }

    /**
     * 异步线程池发送消息
     *
     * @param topicInfo topic 信息
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public static <T> void sendAsyMessage(TopicInfo topicInfo, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo, message);
        SendMsgThread sendMsgThread = new SendMsgThread(topicInfo, message, traceLogId);
        ThreadPool.getInstance().getPool().execute(sendMsgThread);
    }

    /**
     * 发送单个消息
     *
     * @param topicInfo topic 信息
     * @param key key值（影响进入partition）
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public static <T> void sendMessage(TopicInfo topicInfo, int key, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo, message);
        ProducerFactory.getProducer(topicInfo)
            .send(new KeyedMessage(topicInfo.getTopicName(), key, message));
    }

}
