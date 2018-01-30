参考[tiny-spring](https://github.com/code4craft/tiny-spring)

### [IoC](http://www.cnblogs.com/liuhaorain/p/3747470.html#title_3)
#### step4
1. `AbstractBeanDefinitionReader`中的registry（类型为`Map<String, BeanDefinition> registry`）已经有了bean的工厂的意义，但是在`AbstractBeanDefinitionReader`并没有将`BeanDefinition`中的`bean`字段实例化而已。

2. 上面的表述的正确的`AbstractBeanDefinitionReader`中的`registry`是一个`HashMap<String, BeanDefinition>`；
而`AbstractBeanFactory`的`beanDefinitionMap`是`ConcurrentHashMap<String, BeanDefinition>()`。
我个人理解之所以要怎么做，是为了各司其职：
    - `AbstractBeanDefinitionReader`是为了读取xml中定义的bean信息
    - `AbstractBeanFactory`是为了的根据上面获取到信息实例化`bean`（`BeanFactory.registerBeanDefinition`）和根据`beanName`获取到`bean`实例（`BeanFactory.getBean`）.

3. 实例化bean（`BeanFactory.registerBeanDefinition`）完全是利用反射完成：
    - 生成对象是`class.newInstance`
    - 对象属性赋值`Field.set(bean, propertyValue.getValue())`，根据字段名获取到属性`class.getDeclaredField(propertyValue.getName())`

> 不同点：
- xml的解析不采用jdk原生的接口，而是直接使用dom4j。

#### step5：bean之间的依赖注入
1. 为了实现bean之间的依赖，解决问题的关键是怎么知道这个属性对应的value值是的自定义的bean？很简单：在xml中写的`ref`，而不是`value`。在代码中怎么体现value是bean？自顶一个`BeanReference`，在解析xml的时候，讲value解析成为一个`BeanReference`对象，这样就区分开了。除此之外（解析xml的不同地方），在实例化对象之后给对象添加propertyValue的时候，也要区分。

2. 引发问题：当一个bean A的ref为另一个bean B的时候，要初始化A了，但是B还没有初始化。解决方法：lazy-init。解决思路：在getBean没有获取到的bean的对象的时候，立即去init所以要的bean。
解决步骤：
    - 首先：bean的初始化是放在`doCreateBean`方法中完成的
    - 其次：原来调用`doCreateBean`是在`registerBeanDefinition`中完成的，现在在这个步骤中不需要实例化bean，只需要在`beanDefinitionMap`中注册一下
    - 最后：将`doCreateBean`放到`getBean`中来。（见`AbstractBeanFactory`）


### AOP

### 动态代理方法
- jdk动态代理（必须是基于接口编程）
    - jdk动态代理的织入逻辑需要实现接口InvocationHandler（译：调用 句柄）的invoke方法（注意：实现类中需要有被代理的对象Subject subject）
    - 使用的时候，用的Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)来获取代理对象
- cglib动态代理（基于继承）
    - 织入逻辑需要实现MethodInterceptor（译：方法 拦截）的intercept方法
    - 使用的时候new一个Enhancer（译：增强）,被代理的类用setSuperClass，织入逻辑用setCallback，然后create一个代理对象。
- 两个的重点的都是他们的织入逻辑的书写，也就是实现invocationHandler和MethodInterceptor两个实现类。
- 个人理解其实都是提供了一套动态生成字节码的模板

#### step7
1. 原作者说：
> 在Spring AOP中，我觉得最重要的两个角色，就是我们熟悉的MethodInterceptor和MethodInvocation（这两个角色都是AOP联盟的标准），它们分别对应AOP中两个基本角色：Advice和Joinpoint。Advice定义了在切点指定的逻辑，而Joinpoint则代表切点。

- MethodInterceptor
    - 在CGlib中也是用的MethodInterceptor，但是我们这里用的是AOP联盟中的。实现这个接口的invoke方法，完成Advice的逻辑。具体可以看TimerInterceptor和AdvisedSupport。
    - AdvisedSupport中设置了要被代理的对象和advise。

- MethodInvocation
    - 上面的MethodInterceptor接口只有一个函数invoke的入参就是MethodInvocation实例。TimerInterceptor中的使用该实例的时候就是使用了他的proceed()【译：前进，（沿特定路线）行进】方法
    - 他的实现主要体现在`ReflectiveMethodInvocation`类中，它的主要方法就是proceed，如下:
    ```java
    method.invoke(target, args)
    ```
    而`method`,`target`,`args`就是`ReflectiveMethodInvocation`的三个字段。

- JdkDynamicAopProxy


