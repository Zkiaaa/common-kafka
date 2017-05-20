
package com.demo.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * kafka topic info
 * @author Air
 * @version Id: TopicInfo.java, v 0.1 2017.5.20 10:37 Air Exp $$
 */
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class TopicInfo {

    /**
     * 消息类型 同步、异步
     */
    private ProducerType syncFlag;
    /**
     * Topic 名称
     */
    private String       topicName;
    /**
     * 消息发送后的应答方式
     */
    private RequiredAck  ack;
    /**
     * 消息发送异常后是否可以重复发送（重复时可能消息重复）
     */
    private boolean      retries;
    /**
     * 该消费者所属组名
     */
    private String       groupName;
    /**
     * zookeeper 地址（例：127.0.0.1:2181）
     */
    private String       zkAddress;
    /**
     * 集群地址列表（列：192.168.31.1:9092,192.168.31.1:9092）
     */
    private String       brokerList;

    public TopicInfo(ProducerType syncFlag, String topicName, RequiredAck ack, boolean retries,
                     String groupName, String zkAddress, String brokerList) {
        if (groupName == null || groupName.equals("")) {
            groupName = "default_group";
        }
        this.syncFlag = syncFlag;
        this.topicName = topicName;
        this.ack = ack;
        this.retries = retries;
        this.groupName = groupName;
        this.zkAddress = zkAddress;
        this.brokerList = brokerList;
    }

}
