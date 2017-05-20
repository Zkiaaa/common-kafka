package com.demo.kafka.service.consumer.process;

import org.springframework.stereotype.Service;

/**
 * 业务处理接口
 * @author Air
 * @version Id: BusinessProcessInterface.java, v 0.1 2017.5.20 13:27 Air Exp $$
 */
@Service
public abstract class BusinessProcessInterface<V> {

    /**
     * 业务处理
     * @param args          业务处理参数
     */
    public abstract void doBusiness(V args);
}
