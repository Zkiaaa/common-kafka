package com.demo.kafka.service.producer;

import java.util.List;

import com.demo.kafka.service.nospring.ProducerNoSpringService;
import org.springframework.stereotype.Service;

import com.demo.kafka.model.TopicInfo;
import com.demo.kafka.thread.SendMsgThread;
import com.demo.kafka.thread.ThreadPool;

import lombok.extern.slf4j.Slf4j;

/**
 * kafka 生产发送消息
 * @author Air
 * @version Id: ProducerServiceImpl.java, v 0.1 2017.5.20 12:08 Air Exp $$
 */
@Slf4j
@Service
public class ProducerServiceImpl {

    /**
     * 发送消息集合
     * @param topicInfo topic 信息
     * @param messages   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendMessages(TopicInfo topicInfo, List<T> messages, String traceLogId) {
        log.debug("traceLogId:{},topicInfo:{} messages to send :{}", traceLogId, topicInfo,
            messages);
        ProducerNoSpringService.sendMessages(topicInfo, messages, traceLogId);
    }

    /**
     * 发送单个消息（随机分配到partition）
     * @param topicInfo topic 信息
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendMessage(TopicInfo topicInfo, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo, message);
        ProducerNoSpringService.sendMessage(topicInfo, message, traceLogId);
    }

    /**
     * 异步线程池发送消息
     * @param topicInfo topic 信息
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendAsyMessage(TopicInfo topicInfo, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo, message);
        SendMsgThread sendMsgThread = new SendMsgThread(topicInfo, message, traceLogId);
        ThreadPool.getInstance().getPool().execute(sendMsgThread);
    }

    /**
     * 发送单个消息,分发到不同partition中
     * @param topicInfo topic 信息
     * @param key key值（影响进入partition）
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendMessage(TopicInfo topicInfo, int key, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo, message);
        ProducerNoSpringService.sendMessage(topicInfo, key, message, traceLogId);
    }

}
