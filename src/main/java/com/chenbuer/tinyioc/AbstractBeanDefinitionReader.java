package com.chenbuer.tinyioc;

import com.chenbuer.tinyioc.io.ResourceLoader;

import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by buer on 2018/1/18.
 */
// 抽象类实现接口并不需要复写方法
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
