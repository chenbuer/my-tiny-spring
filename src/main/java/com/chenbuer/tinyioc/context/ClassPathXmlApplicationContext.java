package com.chenbuer.tinyioc.context;

import com.chenbuer.tinyioc.beans.BeanDefinition;
import com.chenbuer.tinyioc.beans.factory.AbstractBeanFactory;
import com.chenbuer.tinyioc.beans.factory.AutowireCapableBeanFactory;
import com.chenbuer.tinyioc.beans.io.ResourceLoader;
import com.chenbuer.tinyioc.beans.xml.XmlBeanDefinitionReader;

import javax.swing.text.StyledEditorKit;
import java.util.Map;

/**
 * Created by buer on 2018/1/20.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        // todo:要是没有配置beanFactory，就用AutowireCapableBeanFactory
        this(new AutowireCapableBeanFactory(), configLocation);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory, String configLocation) throws  Exception{
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry:
             xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
