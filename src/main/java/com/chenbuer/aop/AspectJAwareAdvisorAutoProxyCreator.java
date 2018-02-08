package com.chenbuer.aop;

import com.chenbuer.tinyioc.beans.AbstractBeanDefinitionReader;
import com.chenbuer.tinyioc.beans.BeanPostProcessor;
import com.chenbuer.tinyioc.beans.factory.AbstractBeanFactory;
import com.chenbuer.tinyioc.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * Created by buer on 2018/2/5.
 * 这个是将AOP加入spring时候才增加的，todo：具体是什么作用？
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor :
                advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())){

            }

        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {

    }
}
