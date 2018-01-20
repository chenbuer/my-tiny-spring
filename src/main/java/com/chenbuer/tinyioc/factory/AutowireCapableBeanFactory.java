package com.chenbuer.tinyioc.factory;

import com.chenbuer.tinyioc.BeanDefinition;
import com.chenbuer.tinyioc.BeanReference;
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

    /**
     * 创建一个bean实例
     * @param beanDefinition
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 给创建的bean实例设置属性值
     * @param bean
     * @param beanDefinition
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues().getPropertyValueList();
        for (PropertyValue propertyValue : propertyValueList) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference ref = (BeanReference) value;
                // todo:要是此时被依赖的bean在这个bean定义的后面怎么办
                value = getBean(ref.getName());
            }
            declaredField.set(bean, value);

        }
    }
}
