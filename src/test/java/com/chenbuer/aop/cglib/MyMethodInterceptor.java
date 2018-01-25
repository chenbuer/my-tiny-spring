package com.chenbuer.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by buer on 2018/1/25.
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        try {
            System.out.println("###before");
//            result = proxy.invoke(obj, args);
            result=proxy.invokeSuper(obj, args);
        } catch (Exception e) {

            System.out.println("ex:" + e.getMessage());
        } finally {
            System.out.println("###after");
        }

        return result;
    }

}
