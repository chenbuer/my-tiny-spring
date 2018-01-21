package com.chenbuer.tinyioc.context;

import com.chenbuer.tinyioc.beans.factory.AbstractBeanFactory;

/**
 * Created by buer on 2018/1/20.
 */
public abstract class AbstractApplicationContext implements ApplicationContext{

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 用它进行bean的初始化
     */
    public void refresh() throws Exception {

    }

    @Override
    public Object getBean(String name) throws Exception{
        return beanFactory.getBean(name);
    }
}
