package com.demo.kafka.service.nospring;

import com.demo.kafka.model.TopicInfo;
import com.demo.kafka.service.consumer.process.BusinessProcessInterface;
import com.demo.kafka.service.nospring.manager.ConsumeMessageManager;

import lombok.extern.slf4j.Slf4j;

/**
 * kafka消息消费，不依赖spring
 * @author Air
 * @version Id: ConsumerNoSpringService.java, v 0.1 2017.5.20 13:41 Air Exp $$
 */
@Slf4j
public class ConsumerNoSpringService {

    /**
     * 消费消息（会调用 BusinessProcessInterface 的实现）
     *
     * @param topicInfo topic
     * @param beanName 业务实现类
     * @param traceLogId logId
     */
    public static void consumerMessages(final TopicInfo topicInfo,
                                        final Class<? extends BusinessProcessInterface> beanName,
                                        final String traceLogId) throws InstantiationException,
                                                                 IllegalAccessException {
        log.info("consumer message topic info:{},beanName:{},traceLogId:{}", topicInfo, beanName,
            traceLogId);
        final BusinessProcessInterface businessProcess = ConsumeMessageManager
            .getBusinessProcessProxy(topicInfo, beanName);
        ConsumeMessageManager.process(topicInfo, businessProcess, traceLogId);
    }
}
