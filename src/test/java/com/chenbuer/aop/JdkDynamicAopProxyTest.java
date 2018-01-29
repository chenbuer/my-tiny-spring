package com.chenbuer.aop;


import com.chenbuer.tinyioc.HelloWorldService;
import com.chenbuer.tinyioc.context.ApplicationContext;
import com.chenbuer.tinyioc.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("hw");
        helloWorldService.sayHello();

        // 设置被代理的对象（JoinPoint）
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(HelloWorldService.class, helloWorldService);
        advisedSupport.setTargetSource(targetSource);

        // 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 创建代理（Proxy）
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService proxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        // 基于AOP调用
        proxy.sayHello();
    }
}

