package com.chenbuer.tinyioc.xml;

import com.chenbuer.tinyioc.AbstractBeanDefinitionReader;
import com.chenbuer.tinyioc.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by buer on 2018/1/18.
 */
public class XmlBeanDefintionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefintionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException, IOException {
        // 利用dom4j去解析xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        // 开始具体的解析xml
        registerBeanDefinitions(document);
        inputStream.close();

    }

    public void registerBeanDefinitions(Document document) {
        Element rootElement = document.getRootElement();
        parseBeanDefinitions(rootElement);

    }

    private void parseBeanDefinitions(Element rootElement) {
        rootElement.
    }
}
