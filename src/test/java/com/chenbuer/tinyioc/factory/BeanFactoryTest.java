package com.chenbuer.tinyioc.factory;

import com.chenbuer.tinyioc.HelloWorldServiceImpl;
import com.chenbuer.tinyioc.OutputService;
import com.chenbuer.tinyioc.beans.BeanDefinition;
import com.chenbuer.tinyioc.beans.factory.AbstractBeanFactory;
import com.chenbuer.tinyioc.beans.factory.AutowireCapableBeanFactory;
import com.chenbuer.tinyioc.beans.io.ResourceLoader;
import com.chenbuer.tinyioc.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * Created by buer on 2018/1/20.
 */
public class BeanFactoryTest {

    @Test
    public void test() throws Exception{
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.实例化所有的bean
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        AbstractBeanFactory factory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanInfo : registry.entrySet()) {
            factory.registerBeanDefinition(beanInfo.getKey(),beanInfo.getValue());
        }

        // 3.获取到想要的bean
        HelloWorldServiceImpl hw = (HelloWorldServiceImpl) factory.getBean("hw");
        System.out.println(hw);

        OutputService outputService = (OutputService) factory.getBean("outputService");
        System.out.println(outputService);
    }
}
