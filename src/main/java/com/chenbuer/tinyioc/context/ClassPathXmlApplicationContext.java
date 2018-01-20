package com.chenbuer.tinyioc.context;

import com.chenbuer.tinyioc.beans.BeanDefinition;
import com.chenbuer.tinyioc.beans.factory.AbstractBeanFactory;

/**
 * Created by buer on 2018/1/20.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{


    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public void refresh() throws Exception {
        super.refresh();
    }
}
