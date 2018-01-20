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

#### step5：bean之间的依赖注入
1. 为了实现bean之间的依赖，解决问题的关键是怎么知道这个属性对应的value值是的自定义的bean？很简单：在xml中写的`ref`，而不是`value`。在代码中怎么体现value是bean？自顶一个`BeanReference`，在解析xml的时候，讲value解析成为一个`BeanReference`对象，这样就区分开了。除此之外（解析xml的不同地方），在实例化对象之后给对象添加propertyValue的时候，也要区分。

2. 出现问题：当一个bean A的ref为另一个bean B的时候，要初始化A了，但是B还没有初始化。解决方法：lazy-init。解决思路：在getBean没有获取到的bean的对象的时候，立即去init所以要的bean。
解决步骤：
    - 首先：bean的初始化是放在`doCreateBean`方法中完成的
    - 其次：原来调用`doCreateBean`是在`registerBeanDefinition`中完成的，现在在这个步骤中不需要实例化bean，只需要在`beanDefinitionMap`中注册一下
    - 最后：将`doCreateBean`放到getBean中来。（见`AbstractBeanFactory`）




