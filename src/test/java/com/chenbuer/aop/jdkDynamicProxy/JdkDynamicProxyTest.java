package com.chenbuer.aop.jdkDynamicProxy;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 两种获得代理对象的方法
 *
 * Created by buer on 2018/1/24.
 */
public class JdkDynamicProxyTest {

    @Test
    public void test1() throws Exception{
        Class<?> proxyClass = Proxy.getProxyClass(JdkDynamicProxyTest.class.getClassLoader(),
                Subject.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        MyInvocationHandler proxyHandler = new MyInvocationHandler(new MySubject());
        Subject proxyObj = (Subject) constructor.newInstance(proxyHandler);
        proxyObj.sayHello();
    }

    @Test
    public void test2() throws Exception{
        Subject proxyObj = (Subject) Proxy.newProxyInstance(JdkDynamicProxyTest.class.getClassLoader(),
                new Class[]{Subject.class},
                new MyInvocationHandler(new MySubject()));
        proxyObj.sayHello();
    }
}
