package com.chenbuer.tinyioc.factory;

import com.chenbuer.tinyioc.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by buer on 2018/1/20.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


    @Override
    public Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (null == beanDefinition){
            // todo: beanDefintion都是什么时候被置的值
            throw new IllegalArgumentException("no bean named "+ name + "is defined.");
        }
        Object bean = beanDefinition.getBean();
        if (null == bean){
            // todo:这个时候才表示该bean没有被实例化
            doCreateBean(beanDefinition);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException;
}
