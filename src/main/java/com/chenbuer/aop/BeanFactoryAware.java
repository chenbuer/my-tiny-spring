package com.chenbuer.aop;

import com.chenbuer.tinyioc.beans.factory.BeanFactory;

/**
 * Created by buer on 2018/2/5.
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
