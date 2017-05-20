package com.demo.kafka.service.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.demo.kafka.model.TopicInfo;
import com.demo.kafka.service.consumer.process.BusinessProcessInterface;
import com.demo.kafka.service.nospring.manager.ConsumeMessageManager;

import lombok.extern.slf4j.Slf4j;

/**
 * kafka 消息消费实现
 * @author Air
 * @version Id: ConsumerServiceImpl.java, v 0.1 2017.5.20 13:25 Air Exp $$
 */
@Slf4j
@Service
public class ConsumerServiceImpl {
    @Autowired
    private ApplicationContext context;

    /**
     * 消费消息（会调用 BusinessProcessInterface 的实现）
     *
     * @param topicInfo topic
     * @param beanName 业务实现类
     * @param traceLogId logId
     */
    public void consumerMessages(final TopicInfo topicInfo,
                                 final Class<? extends BusinessProcessInterface> beanName,
                                 final String traceLogId) {
        log.info("consumer message topic info:{},beanName:{},traceLogId:{}", topicInfo, beanName,
            traceLogId);
        final BusinessProcessInterface businessProcess = context.getBean(beanName);
        ConsumeMessageManager.process(topicInfo, businessProcess, traceLogId);
        log.info("process done.");
    }
}
