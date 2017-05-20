package com.demo.kafka.service.consumer.process;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理类
 * @author Air
 * @version Id: ProcessInvocationHandler.java, v 0.1 2017.5.20 13:27 Air Exp $$
 */
@Slf4j
public class ProcessInvocationHandler implements InvocationHandler {

    private BusinessProcessInterface processInterface;

    public ProcessInvocationHandler(BusinessProcessInterface processInterface) {
        this.processInterface = processInterface;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log.debug("handel: Object:{},method :{},param:{}", processInterface, method, objects);
        return method.invoke(processInterface, objects);
    }
}
