参考[tiny-spring](https://github.com/code4craft/tiny-spring)

### 理解：
#### step4
1. AbstractBeanDefinitionReader中的registry（类型为Map<String, BeanDefinition> registry）已经有了bean的工厂的意义，但是在AbstractBeanDefinitionReader并没有将BeanDefinition中的bean字段实例化而已。

2. 上面的表述的正确的`AbstractBeanDefinitionReader`中的`registry`是一个HashMap<String, BeanDefinition>；
而`AbstractBeanFactory`的`beanDefinitionMap`是ConcurrentHashMap<String, BeanDefinition>()。
我个人理解之所以要怎么做，是为了各司其职：
    - `AbstractBeanDefinitionReader`是为了读取xml中定义的bean信息
    - `AbstractBeanFactory`是为了的根据上面获取到信息实例化bean（BeanFactory.registerBeanDefinition）和根据beanName获取到bean实例（BeanFactory.getBean）.

3. 实例化bean（`BeanFactory.registerBeanDefinition`）完全是利用反射完成：
    - 生成对象是`class.newInstance`
    - 对象属性赋值`Field.set(bean, propertyValue.getValue())`，根据字段名获取到属性`class.getDeclaredField(propertyValue.getName())`

> 不同点：
- xml的解析不采用jdk原生的接口，而是直接使用dom4j。