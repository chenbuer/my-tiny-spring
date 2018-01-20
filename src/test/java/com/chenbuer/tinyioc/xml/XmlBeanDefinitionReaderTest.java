package com.chenbuer.tinyioc.xml;

import com.chenbuer.tinyioc.beans.BeanDefinition;
import com.chenbuer.tinyioc.beans.io.ResourceLoader;
import com.chenbuer.tinyioc.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * Created by buer on 2018/1/20.
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void test() throws Exception{
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        System.out.println(registry.size());
    }
}
