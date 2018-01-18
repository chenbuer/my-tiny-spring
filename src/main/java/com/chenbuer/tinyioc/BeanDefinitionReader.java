package com.chenbuer.tinyioc;

/**
 * Created by buer on 2018/1/18.
 */
public interface BeanDefinitionReader {
    // todo:他的作用是的将xml的bean定义加载到工厂中？
    void loadBeanDefinitions(String location) throws Exception;
}
