package com.chenbuer.tinyioc;

import com.chenbuer.tinyioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by buer on 2018/1/18.
 */
// 抽象类实现接口并不需要复写方法
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    // buer:这个registry就是所有bean和他name存储的大工厂，可以这么理解，但是不准确
    // todo:个人理解这里的registry就有了factory的作用，只不过这里的BeanDefiniton中的bean没有实例化而已
    private Map<String, BeanDefinition> registry;//registry:注册表

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
