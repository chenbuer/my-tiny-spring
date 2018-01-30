package com.chenbuer.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 这个是织入的方法，并且实现了aop的标准，【注意：但是不实现织入的逻辑。重点是proceed()方法】
 * Created by buer on 2018/1/21.
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    private Object target;

    private Method method;

    private Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
