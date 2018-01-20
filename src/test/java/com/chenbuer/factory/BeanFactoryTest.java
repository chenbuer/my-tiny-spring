package com.chenbuer.factory;

import com.chenbuer.HelloWorldService;
import com.chenbuer.OutputService;
import com.chenbuer.tinyioc.BeanDefinition;
import com.chenbuer.tinyioc.factory.AutowireCapableBeanFactory;
import com.chenbuer.tinyioc.factory.BeanFactory;
import com.chenbuer.tinyioc.io.ResourceLoader;
import com.chenbuer.tinyioc.xml.XmlBeanDefinitionReader;
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
        BeanFactory factory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanInfo : registry.entrySet()) {
            factory.registerBeanDefinition(beanInfo.getKey(),beanInfo.getValue());
        }

        // 3.获取到想要的bean
        HelloWorldService hw = (HelloWorldService) factory.getBean("hw");
        System.out.println(hw);

        OutputService outputService = (OutputService) factory.getBean("hw");
        System.out.println(outputService);
    }
}
