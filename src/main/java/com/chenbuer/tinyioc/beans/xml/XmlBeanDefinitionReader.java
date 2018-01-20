package com.chenbuer.tinyioc.beans.xml;

import com.chenbuer.tinyioc.*;
import com.chenbuer.tinyioc.beans.AbstractBeanDefinitionReader;
import com.chenbuer.tinyioc.beans.BeanDefinition;
import com.chenbuer.tinyioc.beans.PropertyValue;
import com.chenbuer.tinyioc.beans.PropertyValues;
import com.chenbuer.tinyioc.beans.io.ResourceLoader;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by buer on 2018/1/18.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
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

    // 解析所有的bean节点信息
    private void parseBeanDefinitions(Element rootElement) {
        // rootElement是beans根节点
        Iterator elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()){
            // 获取到一个bean节点
            Element bean = (Element) elementIterator.next();
            parseBeanDefinition(bean);
        }

    }

    // 解析一个bean节点信息
    private void parseBeanDefinition(Element bean) {
        // 先获取到这个bean节点的所有属性
        List<Attribute> beanAttrs = bean.attributes();
        String name = null;
        String className = null;
        for (Attribute attribute : beanAttrs) {
            // 在属性中找到name和class名称
            if ("name".equals(attribute.getName())){
                name = attribute.getValue();
            }else if ("class".equals(attribute.getName())){
                className = attribute.getValue();
            }
        }

        // 获取到的name和class名称之后实例化的bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        // 将子节点定义的PropertyValue设置到bean里面
        processProperty(bean, beanDefinition);
        // 将这个子节点设置到Map中！！！！！
        getRegistry().put(name, beanDefinition);
    }

    // 处理每一个bean属性，将这个bean定义的所有属性放到propertiesValues中去
    private void processProperty(Element bean, BeanDefinition beanDefinition) {
        Iterator properties = bean.elementIterator();
        while (properties.hasNext()){
            // 获取到一个属性节点
            Element property = (Element) properties.next();
            List<Attribute> propertyAttrs = property.attributes();
            // 获取到属性名 && 获取到属性值
            String name = null;
            String value = null;
            String ref = null;// 要是是注入的bean，写ref
            for (Attribute propertyAttr :
                    propertyAttrs) {
                if ("name".equals(propertyAttr.getName())){
                    name = propertyAttr.getValue();
                }else if ("value".equals(propertyAttr.getName())){
                    value = propertyAttr.getValue();
                }else if ("ref".equals(propertyAttr.getName())){
                    ref = propertyAttr.getValue();
                }
            }

            if (name == null || name.length() == 0){
                // 要是name没有取到值的话，那就没用了
                // todo:加一个日志打印
                continue;
            }

            // 要是value是有值得话，那就是普通字段注入
            if (null != value && value.length() > 0){
                // 这样就组成了一个属性键值对
                PropertyValue propertyValue = new PropertyValue(name,value);
                // 将刚刚获取到的propertyValue设置进bean中
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                propertyValues.addPropertyValue(propertyValue);
            }else if (ref != null && ref.length() > 0){
                // 这样就是bean的依赖注入
                BeanReference beanReference = new BeanReference(ref);
                // 这样就组成了一个属性键值对
                PropertyValue propertyValue = new PropertyValue(name,beanReference);
                // 将刚刚获取到的propertyValue设置进bean中
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                propertyValues.addPropertyValue(propertyValue);
            }

        }
    }
}
