package com.chenbuer.tinyioc.beans;

/**
 * 用来将aop整合到spring中去
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
