package com.chenbuer.tinyioc.factory;

import com.chenbuer.tinyioc.BeanDefinition;

/**
 * Created by buer on 2018/1/20.
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
