package com.chenbuer.tinyioc.beans;

/**
 * Created by buer on 2018/1/18.
 */
public interface BeanDefinitionReader {
    // todo:他的作用是的将xml的bean定义加载到工厂中？
    // 解释：其实都不能叫做bean工厂，因为registry里面并没有实例化bean，只是获取到了xml中的配置信息
    void loadBeanDefinitions(String location) throws Exception;
}
