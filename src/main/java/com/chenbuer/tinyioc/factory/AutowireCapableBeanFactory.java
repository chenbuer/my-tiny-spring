package com.chenbuer.tinyioc.factory;

import com.chenbuer.tinyioc.BeanDefinition;
import com.chenbuer.tinyioc.PropertyValue;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by buer on 2018/1/20.
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object bean = createBeanInstance(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues().getPropertyValueList();
        for (PropertyValue propertyValue : propertyValueList) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            declaredField.set(bean, propertyValue.getValue());

        }
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }
}
