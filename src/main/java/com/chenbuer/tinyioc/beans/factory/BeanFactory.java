package com.chenbuer.tinyioc.beans.factory;

import com.chenbuer.tinyioc.beans.BeanDefinition;

/**
 * Created by buer on 2018/1/20.
 */
public interface BeanFactory {

    Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
}
