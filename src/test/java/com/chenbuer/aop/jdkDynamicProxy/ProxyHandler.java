package com.chenbuer.aop.jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by buer on 2018/1/24.
 */
public class ProxyHandler implements InvocationHandler {

    private Subject subject ;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("##before");
        Object result = method.invoke(subject, args);
        System.out.println("##after");
        return result;
    }
}