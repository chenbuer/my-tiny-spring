参考[tiny-spring](https://github.com/code4craft/tiny-spring)

### 理解：
1. AbstractBeanDefinitionReader中的registry（类型为Map<String, BeanDefinition> registry）已经有了bean的工厂的意义，但是在AbstractBeanDefinitionReader并没有将BeanDefinition中的bean字段实例化而已。


> 不同点：
- xml的解析不采用jdk原生的接口，而是直接使用dom4j。