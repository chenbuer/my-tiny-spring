package com.chenbuer.aop;

import com.chenbuer.tinyioc.beans.AbstractBeanDefinitionReader;
import com.chenbuer.tinyioc.beans.BeanPostProcessor;
import com.chenbuer.tinyioc.beans.factory.AbstractBeanFactory;
import com.chenbuer.tinyioc.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by buer on 2018/2/5.
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor){
            return bean;
        }
        if (bean instanceof MethodInterceptor){
            return bean;
        }

        beanFactory.getbeasf
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {

    }
}
